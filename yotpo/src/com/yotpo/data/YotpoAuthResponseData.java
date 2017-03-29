package com.yotpo.data;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSetter;


/**
 * Created by haseeb
 */
public class YotpoAuthResponseData
{
	private String accessToken;
	private String tokenType;
	private String authError;
	private YotpoResponseStatus status;
	private boolean error;

	/**
	 * @return the accessToken
	 */
	public String getAccessToken()
	{
		return accessToken;
	}

	/**
	 * @param accessToken
	 *           the accessToken to set
	 */
	@JsonSetter("access_token")
	public void setAccessToken(final String accessToken)
	{
		this.accessToken = accessToken;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType()
	{
		return tokenType;
	}

	/**
	 * @param tokenType
	 *           the tokenType to set
	 */
	@JsonSetter("token_type")
	public void setTokenType(final String tokenType)
	{
		this.tokenType = tokenType;
	}

	/**
	 * @return the error
	 */
	public String getAuthError()
	{
		return authError;
	}

	/**
	 * @param authError
	 *           the error to set
	 */
	@JsonSetter("error")
	public void setAuthError(final String authError)
	{
		this.authError = authError;
	}

	/**
	 * @return the status
	 */
	public YotpoResponseStatus getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *           the status to set
	 */
	@JsonSetter("status")
	public void setStatus(final YotpoResponseStatus status)
	{
		this.status = status;
	}

	/**
	 * @return the error
	 */
	public boolean isError()
	{
		return error;
	}

	/**
	 * @param error
	 *           the error to set
	 */
	@JsonIgnore
	public void setError(final boolean error)
	{
		this.error = error;
	}
}
