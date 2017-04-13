package com.yotpo.order.service;

import com.yotpo.data.YotpoExportRequestData;
import com.yotpo.data.YotpoFeedData;
import com.yotpo.data.YotpoOrderPayloadData;
import com.yotpo.model.service.config.YotpoModel;


/**
 * Created by haseeb
 */
public interface YotpoExportOrderService
{
	/**
	 * Export orders to yotpo, authenticate and parse the response from yotpo.
	 */
	void exportOrderToYotpo(YotpoOrderPayloadData yotpoOrderPayload, YotpoModel yotpoConfig, YotpoExportRequestData yotpoExportDataRequest,
			YotpoFeedData yotpoFeedData);
}
