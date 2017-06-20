package com.yotpo.data;

import org.codehaus.jackson.annotate.JsonSetter;


/**
 * Created by haseeb
 */
public class YotpoProductData
{
	private String productName;
	private String description;
	private String imageURL;
	private String productURL;
	private Double price;
	private String baseProduct;

	/**
	 * @return the productName
	 */
	public String getProductName()
	{
		return productName;
	}

	/**
	 * @param productName
	 *           the productName to set
	 */
	@JsonSetter("name")
	public void setProductName(final String productName)
	{
		this.productName = productName;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *           the description to set
	 */
	@JsonSetter("description")
	public void setDescription(final String description)
	{
		this.description = description;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL()
	{
		return imageURL;
	}

	/**
	 * @param imageURL
	 *           the imageURL to set
	 */
	@JsonSetter("image")
	public void setImageURL(final String imageURL)
	{
		this.imageURL = imageURL;
	}

	/**
	 * @return the productURL
	 */
	public String getProductURL()
	{
		return productURL;
	}

	/**
	 * @param productURL
	 *           the productURL to set
	 */
	@JsonSetter("url")
	public void setProductURL(final String productURL)
	{
		this.productURL = productURL;
	}

	/**
	 * @return the price
	 */
	public Double getPrice()
	{
		return price;
	}

	/**
	 * @param price
	 *           the price to set
	 */
	@JsonSetter("price")
	public void setPrice(final Double price)
	{
		this.price = price;
	}

	/**
	 *
	 * @return
	 */
	public String getBaseProduct()
	{
		return baseProduct;
	}

	/**
	 *
	 * @param baseProduct
	 */
	@JsonSetter("group_id")
	public void setBaseProduct(final String baseProduct)
	{
		this.baseProduct = baseProduct;
	}
}
