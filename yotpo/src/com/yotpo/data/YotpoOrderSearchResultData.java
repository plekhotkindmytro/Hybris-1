package com.yotpo.data;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;


/**
 * Created by haseeb
 */
public class YotpoOrderSearchResultData
{
	private List<OrderModel> orders;
	private YotpoPagingData pagingModel;

	/**
	 * @return the orders
	 */
	public List<OrderModel> getOrders()
	{
		return orders;
	}

	/**
	 * @param orders
	 *           the orders to set
	 */
	public void setOrders(final List<OrderModel> orders)
	{
		this.orders = orders;
	}

	/**
	 * @return the pagingModel
	 */
	public YotpoPagingData getPagingModel()
	{
		return pagingModel;
	}

	/**
	 * @param pagingModel
	 *           the pagingModel to set
	 */
	public void setPagingModel(final YotpoPagingData pagingModel)
	{
		this.pagingModel = pagingModel;
	}
}
