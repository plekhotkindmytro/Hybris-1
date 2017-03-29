package com.yotpo.data;

import org.codehaus.jackson.annotate.JsonSetter;


/**
 * Created by haseeb
 */
public class YotpoAuthenticationData
{
	private String clientID;
	private String clientSecret;
	private String grantType;

	/**
	 * @return the clientKey
	 */
	public String getClientID()
	{
		return clientID;
	}

	/**
	 * @param clientKey
	 *           the clientKey to set
	 */
	@JsonSetter("client_id")
	public void setClientID(final String clientKey)
	{
		this.clientID = clientKey;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret()
	{
		return clientSecret;
	}

	/**
	 * @param clientSecret
	 *           the clientSecret to set
	 */
	@JsonSetter("client_secret")
	public void setClientSecret(final String clientSecret)
	{
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the grantType
	 */
	public String getGrantType()
	{
		return grantType;
	}

	/**
	 * @param grantType
	 *           the grantType to set
	 */
	@JsonSetter("grant_type")
	public void setGrantType(final String grantType)
	{
		this.grantType = grantType;
	}
}
