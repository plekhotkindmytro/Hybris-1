package com.yotpo.listener;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.yotpo.config.service.YotpoConfigurationService;
import com.yotpo.data.YotpoFeedData;
import com.yotpo.event.YotpoExportDataEvent;
import com.yotpo.model.service.config.YotpoModel;
import com.yotpo.order.service.YotpoOrderService;
import com.yotpo.util.YotpoUtils;


/**
 * Created by haseeb
 */
public class YotpoUploadDataEventListener extends AbstractEventListener<YotpoExportDataEvent>
{

	private final static Logger LOG = Logger.getLogger(YotpoUploadDataEventListener.class);

	@Resource
	private YotpoOrderService yotpoOrderService;
	@Resource
	private YotpoConfigurationService yotpoConfigurationService;
	@Resource
	private ConfigurationService configurationService;

	@Override
	protected void onEvent(final YotpoExportDataEvent yotpoExportDataEvent)
	{
		try
		{
			final YotpoFeedData yotpoFeedData = validateAndInitializeMandatoryFeedData();
			final YotpoModel yotpoConfiguretion = validateAndInitializeYotpoConfiguration(yotpoExportDataEvent.getSite());

			if (!yotpoConfiguretion.getEnablePurchaseFeed().booleanValue())
			{
				if (yotpoFeedData.isDebugEnabled())
				{
					LOG.debug("Skipping orders for current Site" + "Site : " + yotpoExportDataEvent.getSite().getName()
							+ "Purchase Feed Flag : " + yotpoConfiguretion.getEnablePurchaseFeed());

				}
				return;
			}

			yotpoOrderService.exportOrders(yotpoFeedData, yotpoConfiguretion, yotpoExportDataEvent);
		}
		catch (final Exception e)
		{
			LOG.error("Exception : " + e.toString());
			throw new UnknownIdentifierException("Exception : " + e.toString());
		}
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
		catch (final Exception e)
		{
			LOG.error("The current locale missing mandatory data therefore aborting the process.");
			throw new UnknownIdentifierException("The current locale missing mandatory data therefore aborting the process.");
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
		catch (final Exception e)
		{
			LOG.error("Exception occur while loading yotpoBasePath, yotpoMassOrdersPath, yotpoAuthURL properties : " + e.toString());
			throw new UnknownIdentifierException(
					"Exception occur while loading yotpoBasePath, yotpoMassOrdersPath, yotpoAuthURL properties : " + e.toString());
		}

		try
		{
			final int retryLimit = configurationService.getConfiguration().getInt("yotpo.retrylimit");
			yotpoFeedData.setRetryLimit(retryLimit);
		}
		catch (final Exception e)
		{
			LOG.error("Exception occur while loading retryLimit property, seting it to 3 [default] : " + e.toString());
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
		catch (final Exception e)
		{
			LOG.error("Exception occur while loading Batch Size : " + e.toString());
			throw new UnknownIdentifierException("Exception occur while loading Batch Size : " + e.toString());
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
		catch (final Exception e)
		{
			LOG.error("Exception occur while loading Order Statuses : " + e.toString());
			throw new UnknownIdentifierException("Exception occur while loading Order Statuses : " + e.toString());
		}
	}
}
