package com.yotpo.mapper.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.yotpo.data.YotpoOrderData;
import com.yotpo.data.YotpoProductData;
import com.yotpo.mapper.YotpoFeedDataMapper;
import com.yotpo.resolver.url.YotpoProductUrlResolver;
import com.yotpo.util.YotpoUtils;

/**
 * Created by haseeb
 */
public class YotpoFeedDataMapperImpl implements YotpoFeedDataMapper
{
	private final static Logger LOG = Logger.getLogger(YotpoFeedDataMapperImpl.class);

	@Resource
	private ConfigurationService configurationService;
	@Resource
	private ModelService modelService;
	@Resource
	private YotpoProductUrlResolver urlResolver;

	@Override
	public YotpoOrderData extractYotpoOrderData(final OrderModel orderModel, final YotpoOrderData yotpoOrderData)
	{

		if (orderModel.getUser() == null)
		{
			throw new UnknownIdentifierException("User for OrderModel: " + orderModel.getCode() + " is null");
		}

		final CustomerModel customer = (CustomerModel) orderModel.getUser();
		yotpoOrderData.setCustomerEmail(customer.getContactEmail());
		yotpoOrderData.setCustomerName(customer.getName());

		yotpoOrderData.setProducts(generateYotpoProductsType(orderModel));

		final String creationDate = DateFormatUtils.format(orderModel.getCreationtime(), "yyyy-MM-dd");
		yotpoOrderData.setOrderID(orderModel.getCode());
		yotpoOrderData.setCreationDate(creationDate);
		yotpoOrderData.setLocale(orderModel.getSite().getLocale());

		yotpoOrderData.setCurrencyISO(orderModel.getCurrency().getIsocode());

		return yotpoOrderData;
	}

	/**
	 *
	 * @param orderModel
	 * @return List<YotpoProductData>
	 */
	private Map<String, YotpoProductData> generateYotpoProductsType(final OrderModel orderModel)
	{

		final Map<String, YotpoProductData> yotpoProducts = new HashMap<String, YotpoProductData>();

		for (final AbstractOrderEntryModel entryModel : orderModel.getEntries())
		{

			final YotpoProductData yotpoProductData = new YotpoProductData();

			if (entryModel.getProduct() == null)
			{
				continue;
			}


			final String name = YotpoUtils.nullToEmpty(entryModel.getProduct().getName());
			final String description = getProductDescription(entryModel.getProduct(), ProductModel.SUMMARY);
			final String baseProductCode = getBaseProductCode(entryModel.getProduct());

			yotpoProductData.setProductName(name);
			yotpoProductData.setDescription(YotpoUtils.nullToEmpty(description));
			yotpoProductData.setBaseProduct(YotpoUtils.nullToEmpty(baseProductCode));

			final String imageUrl = getImageURL(entryModel);
			yotpoProductData.setImageURL(imageUrl);

			final String url = urlResolver.getURL(orderModel.getLanguage().getIsocode(), entryModel.getProduct());
			yotpoProductData.setProductURL(YotpoUtils.nullToEmpty(url));

			yotpoProductData.setPrice(entryModel.getBasePrice());

			yotpoProducts.put(entryModel.getProduct().getCode(), yotpoProductData);


		}
		return yotpoProducts;
	}

	private final String getBaseProductCode(final ProductModel productModel)
	{
		if (productModel instanceof VariantProductModel)
		{
			final ProductModel baseProduct = ((VariantProductModel) productModel).getBaseProduct();
			if (baseProduct != null)
			{
				return baseProduct.getCode();
			}
			return StringUtils.EMPTY;
		}
		return productModel.getCode();
	}

	private final String getProductDescription(final ProductModel productModel, final String attribute)
	{
		final String value = modelService.<String> getAttributeValue(productModel, attribute);
		if (value == null && productModel instanceof VariantProductModel)
		{
			final ProductModel baseProduct = ((VariantProductModel) productModel).getBaseProduct();
			if (baseProduct != null)
			{
				return getProductDescription(baseProduct, attribute);
			}
		}
		return value;
	}

	private final String getImageURL(final AbstractOrderEntryModel productEntry)
	{
		String basePath = StringUtils.EMPTY;
		try
		{
			basePath = configurationService.getConfiguration().getString("yotpo.basepath.http");
		}
		catch (final Exception exception)
		{
			LOG.error("Exception occur while loading yotpo base path, property ", exception);
			throw new UnknownIdentifierException("Exception occur while loading yotpo base path property", exception);
		}

		if (productEntry.getProduct() instanceof VariantProductModel
				&& ((VariantProductModel) productEntry.getProduct()).getBaseProduct().getPicture() != null)
		{

			final String correctUrl = YotpoUtils
					.checkURL(((VariantProductModel) productEntry.getProduct()).getBaseProduct().getPicture().getURL());
			String path = null;
			if (correctUrl == null)
			{
				path = YotpoUtils.appendBaseUrl(
						((VariantProductModel) productEntry.getProduct()).getBaseProduct().getPicture().getURL(), basePath);
			}
			else
			{
				path = ((VariantProductModel) productEntry.getProduct()).getBaseProduct().getPicture().getURL();
			}
			return path;
		}
		else if (productEntry.getProduct().getPicture() != null)
		{
			String path = null;
			final String correctUrl = YotpoUtils.checkURL(productEntry.getProduct().getPicture().getURL());
			if (correctUrl == null)
			{
				path = YotpoUtils.appendBaseUrl(productEntry.getProduct().getPicture().getURL(), basePath);
			}
			else
			{
				path = productEntry.getProduct().getPicture().getURL();
			}
			return path;
		}

		return StringUtils.EMPTY;
	}
}
