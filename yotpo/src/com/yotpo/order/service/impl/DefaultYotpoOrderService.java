package com.yotpo.order.service.impl;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import com.yotpo.config.dao.YotpoOrderDAO;
import com.yotpo.data.YotpoExportRequestData;
import com.yotpo.data.YotpoFeedData;
import com.yotpo.data.YotpoOrderData;
import com.yotpo.data.YotpoOrderPayloadData;
import com.yotpo.data.YotpoOrderSearchCriteriaData;
import com.yotpo.data.YotpoOrderSearchResultData;
import com.yotpo.data.YotpoPagingData;
import com.yotpo.model.service.config.YotpoModel;
import com.yotpo.order.service.YotpoExportOrderService;
import com.yotpo.order.service.YotpoOrderService;
import com.yotpo.populator.YotpoDataPopulator;


/**
 * Created by vijay on 08/02/2017.
 */
public class DefaultYotpoOrderService implements YotpoOrderService
{

	private static String PLATFORM = "general";

	@Resource
	private YotpoDataPopulator yotpoDataPopulator;
	@Resource
	private YotpoOrderDAO yotpoOrderDAO;
	@Resource
	private YotpoExportOrderService yotpoExportOrderService;

	@Override
	public void exportOrders(final YotpoFeedData yotpoFeedData, final YotpoModel yotpoConfiguretion,
			final YotpoExportRequestData yotpoExportDataRequest)
	{

		final CMSSiteModel site = yotpoConfiguretion.getSite();

		//set the locale to get locale specific and site specific orders
		final String locale = new Locale(yotpoConfiguretion.getLanguage().getIsocode(),
				yotpoConfiguretion.getCountry().getIsocode()).toString();
		site.setLocale(locale);

		//Prepare search criteria
		final YotpoOrderSearchCriteriaData yotpoOrderSearchCriteria = new YotpoOrderSearchCriteriaData();

		final YotpoPagingData pagingModel = new YotpoPagingData();
		pagingModel.setIndex(0);
		pagingModel.setBatchSize(yotpoFeedData.getBatchSize());

		yotpoOrderSearchCriteria.setPagingModel(pagingModel);
		yotpoOrderSearchCriteria.setDateTime(yotpoExportDataRequest.getPreviousJobStartTime());
		yotpoOrderSearchCriteria.setSite(site);
		yotpoOrderSearchCriteria.setStatuses(yotpoFeedData.getOrderStatuses());

		//set current time as job start time
		yotpoExportDataRequest.setJobStartTime(new Date());

		do
		{
			final YotpoOrderSearchResultData searchResults = yotpoOrderDAO.findOrderByStatus(yotpoOrderSearchCriteria);
			final List<OrderModel> orders = searchResults.getOrders();

			if (!orders.isEmpty())
			{
				pagingModel.setIndex(pagingModel.getIndex() + pagingModel.getBatchSize());

				//populate yotpo orders
				final List<YotpoOrderData> yotpoOrders = new ArrayList<YotpoOrderData>();
				for (final OrderModel order : orders)
				{
					final YotpoOrderData yotpoOrder = new YotpoOrderData();
					yotpoDataPopulator.populate(order, yotpoOrder);
					yotpoOrders.add(yotpoOrder);
				}
				//prepare yotpo order payload
				final YotpoOrderPayloadData yotpoOrderPayload = new YotpoOrderPayloadData();
				yotpoOrderPayload.setIsValidateData(Boolean.TRUE);
				yotpoOrderPayload.setPlatform(PLATFORM);
				yotpoOrderPayload.setUtokenAuthCode(yotpoConfiguretion.getUtokenAuthCode());
				yotpoOrderPayload.setYotpoOrders(yotpoOrders);

				yotpoExportOrderService.exportOrderToYotpo(yotpoOrderPayload, yotpoConfiguretion, yotpoExportDataRequest,
						yotpoFeedData);
			}
		}
		while (pagingModel.getIndex() < pagingModel.getTotal());
	}
}
