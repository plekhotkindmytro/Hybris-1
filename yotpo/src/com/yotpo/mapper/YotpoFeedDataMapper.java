package com.yotpo.mapper;

import de.hybris.platform.core.model.order.OrderModel;

import com.yotpo.data.YotpoOrderData;


/**
 * Created by haseeb
 */
public interface YotpoFeedDataMapper
{
	/**
	 * extract data from order model and map it to yotpo order data
	 *
	 * @return YotpoOrderData
	 */
	public YotpoOrderData extractYotpoOrderData(final OrderModel orderModel, final YotpoOrderData yotpoOrderData);
}
