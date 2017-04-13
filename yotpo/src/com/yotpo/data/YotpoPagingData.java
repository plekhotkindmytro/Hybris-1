package com.yotpo.data;

/**
 * Created by haseeb
 */
public class YotpoPagingData
{
	private int index;
	private int batchSize;
	private int total;

	/**
	 * @return the start
	 */
	public int getIndex()
	{
		return index;
	}

	/**
	 * @param index
	 *           the start to set
	 */
	public void setIndex(final int index)
	{
		this.index = index;
	}

	/**
	 * @return the range
	 */
	public int getBatchSize()
	{
		return batchSize;
	}

	/**
	 * @param batchSize
	 *           the range to set
	 */
	public void setBatchSize(final int batchSize)
	{
		this.batchSize = batchSize;
	}

	/**
	 * @return the total
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * @param total
	 *           the total to set
	 */
	public void setTotal(final int total)
	{
		this.total = total;
	}


}
