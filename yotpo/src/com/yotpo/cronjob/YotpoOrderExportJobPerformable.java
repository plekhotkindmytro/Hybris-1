package com.yotpo.cronjob;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.yotpo.config.service.YotpoConfigurationService;
import com.yotpo.data.YotpoExportRequestData;
import com.yotpo.data.YotpoFeedData;
import com.yotpo.model.order.dataexport.YotpoOrderExportCronJobModel;
import com.yotpo.model.service.config.YotpoModel;
import com.yotpo.order.service.YotpoOrderService;
import com.yotpo.util.YotpoUtils;



/**
 * Created by vijay on 08/02/2017.
 */
public class YotpoOrderExportJobPerformable extends AbstractJobPerformable<YotpoOrderExportCronJobModel>
{

	private final static Logger LOG = Logger.getLogger(YotpoOrderExportJobPerformable.class);

	@Resource
	private YotpoOrderService yotpoOrderService;
	@Resource
	private YotpoConfigurationService yotpoConfigurationService;
	@Resource
	private ConfigurationService configurationService;
	@Resource
	private ModelService modelService;

	@Override
	public PerformResult perform(final YotpoOrderExportCronJobModel cronJob)
	{
		final YotpoExportRequestData yotpoExportDataRequest = new YotpoExportRequestData();
		populateYotpoRequest(cronJob, yotpoExportDataRequest);
		try
		{
			final YotpoFeedData yotpoFeedData = validateAndInitializeMandatoryFeedData();
			final YotpoModel yotpoConfiguretion = validateAndInitializeYotpoConfiguration(yotpoExportDataRequest.getSite());
			if (!yotpoConfiguretion.getEnablePurchaseFeed().booleanValue())
			{
				if (yotpoFeedData.isDebugEnabled())
				{
					LOG.debug("Skipping orders for current Site" + "Site : " + yotpoExportDataRequest.getSite().getName()
							+ "Purchase Feed Flag : " + yotpoConfiguretion.getEnablePurchaseFeed());

				}
				return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
			}
			yotpoOrderService.exportOrders(yotpoFeedData, yotpoConfiguretion, yotpoExportDataRequest);
		}
		catch (final Exception exception)
		{
			LOG.error("Exporting yotpo feed failed. ", exception);
			return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
		}
		cronJob.setPreviousJobStartTime(yotpoExportDataRequest.getJobStartTime());
		modelService.save(cronJob);
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * populate the yotpo export data request
	 */
	private void populateYotpoRequest(final YotpoOrderExportCronJobModel cronJob, final YotpoExportRequestData event)
	{
		event.setCode(cronJob.getCode());
		event.setSite(cronJob.getSite());

		final Date previousStartTime = cronJob.getPreviousJobStartTime() != null ? cronJob.getPreviousJobStartTime()
				: Date.from(LocalDate.of(2017, Month.JANUARY, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		event.setPreviousJobStartTime(previousStartTime);

	}

	/**
	 * Load yotpo configuration for given site, validate and initialize yotpo configuration.
	 *
	 * @return YotopModel
	 */
	private YotpoModel validateAndInitializeYotpoConfiguration(final CMSSiteModel site)
	{
		try
		{
			final YotpoModel yotpoConfiguretion = yotpoConfigurationService.getYotpoConfiguration(site);
			final boolean isValidate = YotpoUtils.validateMandatoryConfigData(yotpoConfiguretion);
			if (!isValidate)
			{
				LOG.error("The current site missing mandatory data for App Key and ClientSecretKey therefore aborting the process.");
				throw new UnknownIdentifierException(
						"The current site missing mandatory data for App Key and ClientSecretKey therefore aborting the process.");
			}
			return yotpoConfiguretion;
		}
		catch (final Exception exception)
		{
			LOG.error("The current locale missing mandatory data therefore aborting the process.");
			throw new UnknownIdentifierException("The current locale missing mandatory data therefore aborting the process.", exception);
		}
	}

	/**
	 * Load yotpo feed data, validate and initialize feed data.
	 *
	 * @return YotpoFeedData
	 */
	private YotpoFeedData validateAndInitializeMandatoryFeedData()
	{
		final YotpoFeedData yotpoFeedData = new YotpoFeedData();

		try
		{
			final boolean isDebugEnabled = configurationService.getConfiguration().getBoolean("yotpo.debug.enabled");
			yotpoFeedData.setDebugEnabled(isDebugEnabled);
		}
		catch (final Exception e)
		{
			LOG.error("Exception occur while loading Debug Enabled property, seting it to true [default] : " + e.toString());
			yotpoFeedData.setDebugEnabled(true);
		}

		try
		{
			final String yotpoBasePath = configurationService.getConfiguration().getString("yotpo.purchase.base.path");
			final String yotpoMassOrdersPath = configurationService.getConfiguration().getString("yotpo.mass.orders.path");
			final String yotpoAuthURL = configurationService.getConfiguration().getString("yotpo.auth.url");

			yotpoFeedData.setYotpoBasePath(yotpoBasePath);
			yotpoFeedData.setYotpoMassOrdersPath(yotpoMassOrdersPath);
			yotpoFeedData.setYotpoAuthURL(yotpoAuthURL);
		}
		catch (final Exception exception)
		{
			LOG.error("Exception occur while loading yotpoBasePath, yotpoMassOrdersPath, yotpoAuthURL properties", exception);
			throw new UnknownIdentifierException(
					"Exception occur while loading yotpoBasePath, yotpoMassOrdersPath, yotpoAuthURL properties", exception);
		}

		try
		{
			final int retryLimit = configurationService.getConfiguration().getInt("yotpo.retrylimit");
			yotpoFeedData.setRetryLimit(retryLimit);
		}
		catch (final Exception exception)
		{
			LOG.warn("Exception occur while loading retryLimit property, seting it to 3 [default]",exception);
			yotpoFeedData.setRetryLimit(3);
		}

		final List<OrderStatus> orderStatuses = validateAndInitializeOrderStatuses();
		yotpoFeedData.setOrderStatuses(orderStatuses);

		//validate and initialize batch size
		try
		{
			final int batchSize = configurationService.getConfiguration().getInt("yotpo.batch.size");
			yotpoFeedData.setBatchSize(batchSize);
		}
		catch (final Exception exception)
		{
			LOG.error("Exception occur while loading Batch Size ", exception);
			throw new UnknownIdentifierException("Exception occur while loading Batch Size", exception);
		}

		return yotpoFeedData;
	}

	/**
	 * Load yotpo order statuses, validate and initialize order statuses.
	 *
	 * @return YotpoFeedData
	 */
	private List<OrderStatus> validateAndInitializeOrderStatuses()
	{
		try
		{
			final String orderStatusProperties = (String) configurationService.getConfiguration()
					.getProperty("yotpo.order.statuses");

			if (orderStatusProperties == null)
			{
				throw new UnknownIdentifierException("Order Statuses Properties Missing");
			}

			final List<String> orderStatusList = new ArrayList<String>(Arrays.asList(orderStatusProperties.split(",")));
			final List<OrderStatus> orderStatuses = new ArrayList<OrderStatus>();

			for (final String orderStatus : orderStatusList)
			{
				orderStatuses.add(OrderStatus.valueOf(orderStatus));
			}

			return orderStatuses;
		}
		catch (final Exception exception)
		{
			LOG.error("Exception occur while loading Order Statuses ", exception);
			throw new UnknownIdentifierException("Exception occur while loading Order Statuses", exception);
		}
	}
}
