package com.yotpo.data;

import org.codehaus.jackson.annotate.JsonSetter;
import org.springframework.http.HttpStatus;


/**
 * Created by haseeb
 */
public class YotpoResponseStatus
{
	private String message;
	private String errorType;
	private HttpStatus code;

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 *           the message to set
	 */
	@JsonSetter("message")
	public void setMessage(final String message)
	{
		this.message = message;
	}

	/**
	 * @return the errorType
	 */
	public String getErrorType()
	{
		return errorType;
	}

	/**
	 * @param errorType
	 *           the errorType to set
	 */
	@JsonSetter("error_type")
	public void setErrorType(final String errorType)
	{
		this.errorType = errorType;
	}

	/**
	 * @return the code
	 */
	public HttpStatus getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	@JsonSetter("code")
	public void setCode(final HttpStatus code)
	{
		this.code = code;
	}
}
