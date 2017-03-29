package com.yotpo.order.service;

import com.yotpo.data.YotpoFeedData;
import com.yotpo.data.YotpoOrderPayload;
import com.yotpo.event.YotpoExportDataEvent;
import com.yotpo.model.service.config.YotpoModel;


/**
 * Created by haseeb
 */
public interface YotpoExportOrderService
{
	/**
	 * Export orders to yotpo, authenticate and parse the response from yotpo.
	 */
	void exportOrderToYotpo(YotpoOrderPayload yotpoOrderPayload, YotpoModel yotpoConfig, YotpoExportDataEvent yotpoExportDataEvent,
			YotpoFeedData yotpoFeedData);
}
