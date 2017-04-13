package com.yotpo.resolver.url.impl;

import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.yotpo.resolver.url.YotpoProductUrlResolver;
import com.yotpo.util.YotpoUtils;


/**
 * Created by haseeb.
 */
public class YotpoProductUrlResolverImpl implements YotpoProductUrlResolver
{
	private final static Logger LOG = Logger.getLogger(YotpoProductUrlResolverImpl.class);

	@Resource
	private ConfigurationService configurationService;
	@Resource
	private ModelService modelService;
	@Resource
	private UrlResolver<ProductModel> productModelUrlResolver;

	@Override
	public String getURL(final String language, final ProductModel productModel)
	{
		try
		{
			final String baseUrl = configurationService.getConfiguration().getString("yotpo.basepath.http");
			final String yotpostore = configurationService.getConfiguration().getString("yotpo.store.name");

			final String productURL = "/" + yotpostore + "/" + language + productModelUrlResolver.resolve(productModel);
			final String path = YotpoUtils.appendBaseUrl(productURL, baseUrl);

			return path;
		}
		catch (final Exception exception)
		{
			LOG.error("Exception occur while loading yotpo base path, yotpo store name properties ", exception);
			throw new UnknownIdentifierException(
					"Exception occur while loading yotpo base path, yotpo store name properties ", exception);
		}
	}
}
