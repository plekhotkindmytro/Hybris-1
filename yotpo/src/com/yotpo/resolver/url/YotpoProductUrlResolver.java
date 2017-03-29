package com.yotpo.resolver.url;

import de.hybris.platform.core.model.product.ProductModel;


/**
 * Created by haseeb.
 */
public interface YotpoProductUrlResolver
{
	/**
	 * Prepares product url
	 *
	 * @return String
	 */
	String getURL(String language, ProductModel productModel);
}
