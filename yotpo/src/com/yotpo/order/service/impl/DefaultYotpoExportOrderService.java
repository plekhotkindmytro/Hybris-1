package com.yotpo.order.service.impl;

import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.yotpo.data.YotpoAuthResponseData;
import com.yotpo.data.YotpoAuthenticationData;
import com.yotpo.data.YotpoFeedData;
import com.yotpo.data.YotpoOrderPayload;
import com.yotpo.data.YotpoOrderResponseData;
import com.yotpo.data.YotpoResponseStatus;
import com.yotpo.event.YotpoExportDataEvent;
import com.yotpo.model.service.config.YotpoModel;
import com.yotpo.order.service.YotpoExportOrderService;
import com.yotpo.util.YotpoUtils;


/**
 * Created by haseeb
 */
public class DefaultYotpoExportOrderService implements YotpoExportOrderService
{

	private final static Logger LOG = Logger.getLogger(DefaultYotpoExportOrderService.class);

	@Resource
	private ModelService modelService;
	@Resource
	private ConfigurationService configurationService;

	@Override
	public void exportOrderToYotpo(final YotpoOrderPayload yotpoOrderPayload, final YotpoModel yotpoConfig,
			final YotpoExportDataEvent yotpoExportDataEvent, final YotpoFeedData yotpoFeedData)
	{

		final String yotpoAppKey = yotpoConfig.getAppKey();

		final String yotpoURL = YotpoUtils.trimUrl(yotpoFeedData.getYotpoBasePath()) + "/" + yotpoAppKey
				+ yotpoFeedData.getYotpoMassOrdersPath();

		boolean authenticationError = false;
		String orderJSON = prepareOrderJSON(yotpoOrderPayload);
		int retryLimit = yotpoFeedData.getRetryLimit();

		do
		{
			authenticationError = exportOrders(orderJSON, yotpoURL, yotpoFeedData.isDebugEnabled());
			//handle auth error
			if (authenticationError)
			{
				//get auth token form yotpo
				final YotpoAuthResponseData yotpoAuthResponseData = authenticate(yotpoConfig, yotpoFeedData);

				if (yotpoAuthResponseData.isError())
				{
					throw new UnknownIdentifierException("AuthenticateOnYotpo: The authentication request returned error");
				}

				yotpoOrderPayload.setUtokenAuthCode(yotpoAuthResponseData.getAccessToken());
				//update uAuthToken
				yotpoConfig.setUtokenAuthCode(yotpoAuthResponseData.getAccessToken());
				modelService.save(yotpoConfig);

				orderJSON = prepareOrderJSON(yotpoOrderPayload);
			}

		}
		while (authenticationError && --retryLimit >= 0);
	}

	/**
	 * export orders to yotpo, returns True if success
	 * 
	 * @return {@link Boolean}
	 */
	private boolean exportOrders(final String orderJSON, final String yotpoURL, final boolean isDebugEnabled)
	{
		try
		{
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			final HttpEntity<String> httpEntity = new HttpEntity<String>(orderJSON, headers);

			final RestTemplate restTemplate = new RestTemplate();

			final ResponseEntity<String> response = restTemplate.postForEntity(yotpoURL, httpEntity, String.class);

			final YotpoOrderResponseData yotpoResponse = parseYotpoOrderResponse(response);

			final HttpStatus httpStatus = response.getStatusCode();

			if (httpStatus.equals(HttpStatus.OK))
			{

				if (yotpoResponse.getErrors() != null && !yotpoResponse.getErrors().isEmpty())
				{
					LOG.error("ExportOrderToYotpo: Error occured while trying to upload feed Errors : - " + yotpoResponse.getErrors());
					throw new UnknownIdentifierException(
							"ExportOrderToYotpo: Error occured while trying to upload feed Errors : - " + yotpoResponse.getErrors());
				}

				if (isDebugEnabled)
				{
					LOG.debug("ExportOrderToYotpo: Order Feed sumbitted successfully.");
				}
			}
			else if (httpStatus.equals(HttpStatus.UNAUTHORIZED))
			{
				LOG.error("ExportOrderToYotpo: The request to export order failed authentication. Error code: " + httpStatus.value()
						+ "\n Error Text is: " + response.getBody());
				return true;
			}
			else
			{
				LOG.error("ExportOrderToYotpo: Could not export order to Yotpo - HTTP Status Code is: " + httpStatus.value()
						+ "\n Error Text is: " + response.getBody() + "\n Order JSON is: " + orderJSON);

				throw new UnknownIdentifierException(
						"ExportOrderToYotpo: Could final not export order final to Yotpo - final HTTP Status Code is: "
								+ httpStatus.value());

			}
		}
		catch (final Exception ex)
		{
			if (ex instanceof HttpClientErrorException)
			{
				final HttpStatus status = ((HttpClientErrorException) ex).getStatusCode();
				if (status.equals(HttpStatus.UNAUTHORIZED))
				{
					LOG.error("ExportOrderToYotpo: The request to export order failed authentication. Error code: " + status.value()
							+ "\n Error Text is: " + ((HttpClientErrorException) ex).getResponseBodyAsString());
					return true;
				}
			}

			LOG.error("ExportOrderToYotpo: Error occured while trying to upload feed - " + ex.toString());
			throw new UnknownIdentifierException(ex.toString());
		}

		return false;
	}

	/**
	 * authenticate on yotpo and returns resposne data
	 * 
	 * @return YotpoAuthResponseData
	 */
	private YotpoAuthResponseData authenticate(final YotpoModel yotpoConfig, final YotpoFeedData yotpoFeedData)
	{
		final String autenticationJSON = prepareAuthenticationJSON(yotpoConfig.getAppKey(), yotpoConfig.getClientSecretKey());

		final String yotpoAuthURL = yotpoFeedData.getYotpoAuthURL();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<String> httpEntity = new HttpEntity<String>(autenticationJSON, headers);
		final RestTemplate restTemplate = new RestTemplate();

		final ResponseEntity<String> response = restTemplate.postForEntity(yotpoAuthURL, httpEntity, String.class);

		final HttpStatus httpStatus = response.getStatusCode();
		final YotpoAuthResponseData yotpoResponse = parseYotpoResponse(response);

		if (httpStatus.equals(HttpStatus.NOT_FOUND))
		{
			final YotpoResponseStatus status = yotpoResponse.getStatus();
			if (status != null)
			{
				LOG.error("AuthenticateOnYotpo: The authentication request returned error. \n Details are:" + " Error Message - "
						+ status.getMessage() + " Error Code - " + status.getCode() + " Error type - " + status.getErrorType());
			}

			yotpoResponse.setError(true);
		}
		else if (httpStatus.equals(HttpStatus.OK))
		{
			if (StringUtils.isNotBlank(yotpoResponse.getAuthError()))
			{
				LOG.error("AuthenticateOnYotpo: The authentication couldnt be performed. \n Error Message: "
						+ yotpoResponse.getAuthError());
				yotpoResponse.setError(true);
			}
			else
			{
				//in case success
				if (yotpoFeedData.isDebugEnabled())
				{
					LOG.debug("AuthenticateOnYotpo: Authetication performed successfully.");
				}
				yotpoResponse.setError(false);
				return yotpoResponse;
			}
		}
		else
		{
			LOG.error("AuthenticateOnYotpo: Unknown error occured while trying to authenticate with Yotpo - HTTP Status Code is: "
					+ httpStatus.value() + ", Error Text is: " + response.getBody());//TODO test in case of error what is body

			yotpoResponse.setError(true);
		}

		return yotpoResponse;
	}

	/**
	 * prepare order json
	 * 
	 * @return String
	 */
	private String prepareOrderJSON(final YotpoOrderPayload yotpoOrderPayload)
	{

		final ObjectMapper mapper = new ObjectMapper();
		String orderJSON = "";
		try
		{
			orderJSON = mapper.writeValueAsString(yotpoOrderPayload);
		}
		catch (final IOException e)
		{
			throw new UnknownIdentifierException(e.toString());
		}
		return orderJSON;
	}

	/**
	 * prepare authentication json
	 * 
	 * @return String
	 */
	private String prepareAuthenticationJSON(final String appKey, final String clientSecretKey)
	{
		final YotpoAuthenticationData yotpoAuthenticationData = new YotpoAuthenticationData();
		yotpoAuthenticationData.setClientID(appKey);
		yotpoAuthenticationData.setClientSecret(clientSecretKey);
		yotpoAuthenticationData.setGrantType("client_credentials");

		String authenticationJSON = "";
		final ObjectMapper mapper = new ObjectMapper();
		try
		{
			authenticationJSON = mapper.writeValueAsString(yotpoAuthenticationData);
		}
		catch (final IOException e)
		{
			throw new UnknownIdentifierException(e.toString());
		}

		return authenticationJSON;
	}

	/**
	 * parse yotpo auth response
	 * 
	 * @return YotpoAuthResponseData
	 */
	private YotpoAuthResponseData parseYotpoResponse(final ResponseEntity<String> response)
	{
		final ObjectMapper mapper = new ObjectMapper();
		YotpoAuthResponseData yotpoResponse = new YotpoAuthResponseData();
		try
		{
			yotpoResponse = mapper.readValue(response.getBody(), YotpoAuthResponseData.class);
		}
		catch (final IOException e)
		{
			throw new UnknownIdentifierException(e.toString());
		}
		return yotpoResponse;
	}

	/**
	 * parse yotpo feed response
	 * 
	 * @return YotpoOrderResponseData
	 */
	private YotpoOrderResponseData parseYotpoOrderResponse(final ResponseEntity<String> response)
	{
		final ObjectMapper mapper = new ObjectMapper();
		YotpoOrderResponseData yotpoResponse = new YotpoOrderResponseData();
		try
		{
			yotpoResponse = mapper.readValue(response.getBody(), YotpoOrderResponseData.class);
		}
		catch (final IOException e)
		{
			throw new UnknownIdentifierException(e.toString());
		}
		return yotpoResponse;
	}
}
