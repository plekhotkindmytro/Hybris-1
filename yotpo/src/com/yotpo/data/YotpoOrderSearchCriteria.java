package com.yotpo.data;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.enums.OrderStatus;

import java.util.Date;
import java.util.List;


/**
 * Created by haseeb
 */
public class YotpoOrderSearchCriteria
{
	private List<OrderStatus> statuses;
	private CMSSiteModel site;
	private Date dateTime;
	private YotpoPagingModel pagingModel;

	/**
	 * @return the statuses
	 */
	public List<OrderStatus> getStatuses()
	{
		return statuses;
	}

	/**
	 * @param statuses
	 *           the statuses to set
	 */
	public void setStatuses(final List<OrderStatus> statuses)
	{
		this.statuses = statuses;
	}

	/**
	 * @return the site
	 */
	public CMSSiteModel getSite()
	{
		return site;
	}

	/**
	 * @param site
	 *           the site to set
	 */
	public void setSite(final CMSSiteModel site)
	{
		this.site = site;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime()
	{
		return dateTime;
	}

	/**
	 * @param dateTime
	 *           the dateTime to set
	 */
	public void setDateTime(final Date dateTime)
	{
		this.dateTime = dateTime;
	}

	/**
	 * @return the pagingModel
	 */
	public YotpoPagingModel getPagingModel()
	{
		return pagingModel;
	}

	/**
	 * @param pagingModel
	 *           the pagingModel to set
	 */
	public void setPagingModel(final YotpoPagingModel pagingModel)
	{
		this.pagingModel = pagingModel;
	}


}
