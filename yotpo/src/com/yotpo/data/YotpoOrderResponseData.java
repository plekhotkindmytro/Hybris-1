package com.yotpo.data;

import java.util.List;

import org.codehaus.jackson.annotate.JsonSetter;


/**
 * Created by haseeb
 */
public class YotpoOrderResponseData
{
	private Integer code;
	private String message;
	private List<String> errors;

	/**
	 * @return the code
	 */
	public Integer getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *           the code to set
	 */
	@JsonSetter("code")
	public void setCode(final Integer code)
	{
		this.code = code;
	}

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
	 * @return the errors
	 */
	public List<String> getErrors()
	{
		return errors;
	}

	/**
	 * @param errors
	 *           the errors to set
	 */
	@JsonSetter("errors")
	public void setErrors(final List<String> errors)
	{
		this.errors = errors;
	}
}
