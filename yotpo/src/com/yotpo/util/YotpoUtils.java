package com.yotpo.util;

import org.apache.commons.lang3.StringUtils;

import com.yotpo.model.service.config.YotpoModel;


/**
 * Created by haseeb.
 */
public class YotpoUtils
{

	/**
	 * check url stars with http
	 * 
	 * @return String
	 */
	public final static String checkURL(final String URL)
	{

		if (URL.startsWith("http"))
		{

			return URL;
		}

		return null;
	}

	/**
	 * Append base url to given path
	 * 
	 * @return String
	 */
	public final static String appendBaseUrl(final String path, final String baseUrl)
	{
		final String result = trimUrl(baseUrl);

		return result + path;
	}

	/**
	 * Trim url at the end if contains "/"
	 * 
	 * @return String
	 */
	public final static String trimUrl(final String url)
	{
		if (url.endsWith("/"))
		{
			final int length = url.length();
			return url.substring(0, (length - 1));
		}

		return url;
	}

	/**
	 * Null/Empty string safe check
	 * 
	 * @return String
	 */
	public final static String nullToEmpty(final String str)
	{
		if (StringUtils.isNotBlank(str))
		{
			return str;
		}
		return "";
	}

	/**
	 * validate app key and client secret key, if validated return TRUE
	 * 
	 * @return {@link Boolean}
	 */
	public final static boolean validateMandatoryConfigData(final YotpoModel yotpoConfiguretion)
	{

		if (!(StringUtils.isNotBlank(yotpoConfiguretion.getAppKey())
				&& StringUtils.isNotBlank(yotpoConfiguretion.getClientSecretKey())))
		{
			return false;
		}

		return true;
	}

	/**
	 * validate app key, if validated return TRUE
	 * 
	 * @return {@link Boolean}
	 */
	public final static boolean validateMandatoryAppKey(final YotpoModel yotpoConfiguretion)
	{

		if (!StringUtils.isNotBlank(yotpoConfiguretion.getAppKey()))
		{
			return false;
		}

		return true;
	}
}
