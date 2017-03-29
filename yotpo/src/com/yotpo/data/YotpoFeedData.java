package com.yotpo.data;

import de.hybris.platform.core.enums.OrderStatus;

import java.util.List;


/**
 * Created by haseeb
 */
public class YotpoFeedData
{

	private int batchSize;
	private int retryLimit;
	private boolean isDebugEnabled;
	private String yotpoBasePath;
	private String yotpoMassOrdersPath;
	private String yotpoAuthURL;
	private List<OrderStatus> orderStatuses;

	/**
	 * @return the yotpoBasePath
	 */
	public String getYotpoBasePath()
	{
		return yotpoBasePath;
	}

	/**
	 * @param yotpoBasePath
	 *           the yotpoBasePath to set
	 */
	public void setYotpoBasePath(final String yotpoBasePath)
	{
		this.yotpoBasePath = yotpoBasePath;
	}

	/**
	 * @return the yotpoMassOrdersPath
	 */
	public String getYotpoMassOrdersPath()
	{
		return yotpoMassOrdersPath;
	}

	/**
	 * @param yotpoMassOrdersPath
	 *           the yotpoMassOrdersPath to set
	 */
	public void setYotpoMassOrdersPath(final String yotpoMassOrdersPath)
	{
		this.yotpoMassOrdersPath = yotpoMassOrdersPath;
	}

	/**
	 * @return the retryLimit
	 */
	public int getRetryLimit()
	{
		return retryLimit;
	}

	/**
	 * @param retryLimit
	 *           the retryLimit to set
	 */
	public void setRetryLimit(final int retryLimit)
	{
		this.retryLimit = retryLimit;
	}

	/**
	 * @return the yotpoAuthURL
	 */
	public String getYotpoAuthURL()
	{
		return yotpoAuthURL;
	}

	/**
	 * @param yotpoAuthURL
	 *           the yotpoAuthURL to set
	 */
	public void setYotpoAuthURL(final String yotpoAuthURL)
	{
		this.yotpoAuthURL = yotpoAuthURL;
	}

	/**
	 * @return the orderStatuses
	 */
	public List<OrderStatus> getOrderStatuses()
	{
		return orderStatuses;
	}

	/**
	 * @param orderStatuses
	 *           the orderStatuses to set
	 */
	public void setOrderStatuses(final List<OrderStatus> orderStatuses)
	{
		this.orderStatuses = orderStatuses;
	}

	/**
	 * @return the batchSize
	 */
	public int getBatchSize()
	{
		return batchSize;
	}

	/**
	 * @param batchSize
	 *           the batchSize to set
	 */
	public void setBatchSize(final int batchSize)
	{
		this.batchSize = batchSize;
	}

	/**
	 * @return the isDebugEnabled
	 */
	public boolean isDebugEnabled()
	{
		return isDebugEnabled;
	}

	/**
	 * @param isDebugEnabled
	 *           the isDebugEnabled to set
	 */
	public void setDebugEnabled(final boolean isDebugEnabled)
	{
		this.isDebugEnabled = isDebugEnabled;
	}
}
