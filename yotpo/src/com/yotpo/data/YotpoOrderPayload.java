package com.yotpo.data;

import java.util.List;

import org.codehaus.jackson.annotate.JsonSetter;


/**
 * Created by haseeb
 */
public class YotpoOrderPayload
{
	private Boolean isValidateData;
	private String platform;
	private String utokenAuthCode;
	private List<YotpoOrderData> yotpoOrders;

	/**
	 * @return the isValidateData
	 */
	public Boolean getIsValidateData()
	{
		return isValidateData;
	}

	/**
	 * @param isValidateData
	 *           the isValidateData to set
	 */
	@JsonSetter("validate_data")
	public void setIsValidateData(final Boolean isValidateData)
	{
		this.isValidateData = isValidateData;
	}

	/**
	 * @return the utokenAuthCode
	 */
	public String getUtokenAuthCode()
	{
		return utokenAuthCode;
	}

	/**
	 * @param utokenAuthCode
	 *           the utokenAuthCode to set
	 */
	@JsonSetter("utoken")
	public void setUtokenAuthCode(final String utokenAuthCode)
	{
		this.utokenAuthCode = utokenAuthCode;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform()
	{
		return platform;
	}

	/**
	 * @param platform
	 *           the platform to set
	 */
	@JsonSetter("platform")
	public void setPlatform(final String platform)
	{
		this.platform = platform;
	}

	/**
	 * @return the yotpoOrders
	 */
	public List<YotpoOrderData> getYotpoOrders()
	{
		return yotpoOrders;
	}

	/**
	 * @param yotpoOrders
	 *           the yotpoOrders to set
	 */
	@JsonSetter("orders")
	public void setYotpoOrders(final List<YotpoOrderData> yotpoOrders)
	{
		this.yotpoOrders = yotpoOrders;
	}
}
