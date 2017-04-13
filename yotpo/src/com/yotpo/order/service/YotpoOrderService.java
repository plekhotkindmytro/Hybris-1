package com.yotpo.order.service;

import com.yotpo.data.YotpoExportRequestData;
import com.yotpo.data.YotpoFeedData;
import com.yotpo.model.service.config.YotpoModel;


/**
 * Created by vijay on 08/02/2017.
 */
public interface YotpoOrderService
{
	/**
	 * Export orders to yotpo, prepares the search criteria, prepares yotpo payload and export orders.
	 */
	void exportOrders(YotpoFeedData yotpoFeedData, YotpoModel yotpoConfiguretion, YotpoExportRequestData yotpoExportDataRequest);
}
