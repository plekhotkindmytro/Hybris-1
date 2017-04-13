package com.yotpo.data;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import java.util.Date;


/**
 * Created by haseeb
 */
public class YotpoExportRequestData extends AbstractEvent
{
	private String code;
	private CMSSiteModel site;
	private Date previousJobStartTime;
	private Date jobStartTime;

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	public void setCode(final String code)
	{
		this.code = code;
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
	 * @return the orderFeedJobLastExecutionDateTime
	 */
	public Date getPreviousJobStartTime()
	{
		return previousJobStartTime;
	}

	/**
	 * @param previousJobStartTime
	 *           the orderFeedJobLastExecutionDateTime to set
	 */
	public void setPreviousJobStartTime(final Date previousJobStartTime)
	{
		this.previousJobStartTime = previousJobStartTime;
	}

	@Override
	public String toString()
	{
		return "yotpoExportDataRequest [code=" + code + ", site=" + site + ", previousJobStartTime=" + previousJobStartTime + "]";
	}

	/**
	 * @return the jobStartTime
	 */
	public Date getJobStartTime()
	{
		return jobStartTime;
	}

	/**
	 * @param jobStartTime
	 *           the jobStartTime to set
	 */
	public void setJobStartTime(final Date jobStartTime)
	{
		this.jobStartTime = jobStartTime;
	}


}
