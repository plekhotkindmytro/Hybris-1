package com.yotpo.data;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonSetter;


/**
 * Created by haseeb
 */
public class YotpoOrderData
{

	private String orderID;
	private String creationDate;
	private String currencyISO;
	private String customerName;
	private String customerEmail;
	private String locale;
	private Map<String, YotpoProductData> products;

	/**
	 * @return the orderID
	 */
	public String getOrderID()
	{
		return orderID;
	}

	/**
	 * @param orderID
	 *           the orderID to set
	 */
	@JsonSetter("order_id")
	public void setOrderID(final String orderID)
	{
		this.orderID = orderID;
	}

	/**
	 * @return the creationDate
	 */
	public String getCreationDate()
	{
		return creationDate;
	}

	/**
	 * @param creationDate
	 *           the creationDate to set
	 */
	@JsonSetter("order_date")
	public void setCreationDate(final String creationDate)
	{
		this.creationDate = creationDate;
	}

	/**
	 * @return the currencyISO
	 */
	public String getCurrencyISO()
	{
		return currencyISO;
	}

	/**
	 * @param currencyISO
	 *           the currencyISO to set
	 */
	@JsonSetter("currency_iso")
	public void setCurrencyISO(final String currencyISO)
	{
		this.currencyISO = currencyISO;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName()
	{
		return customerName;
	}

	/**
	 * @param customerName
	 *           the customerName to set
	 */
	@JsonSetter("customer_name")
	public void setCustomerName(final String customerName)
	{
		this.customerName = customerName;
	}

	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail()
	{
		return customerEmail;
	}

	/**
	 * @param customerEmail
	 *           the customerEmail to set
	 */
	@JsonSetter("email")
	public void setCustomerEmail(final String customerEmail)
	{
		this.customerEmail = customerEmail;
	}

	/**
	 * @return the locale
	 */
	public String getLocale()
	{
		return locale;
	}

	/**
	 * @param locale
	 *           the locale to set
	 */
	@JsonIgnore
	public void setLocale(final String locale)
	{
		this.locale = locale;
	}

	/**
	 * @return the products
	 */
	public Map<String, YotpoProductData> getProducts()
	{
		return products;
	}

	/**
	 * @param products
	 *           the products to set
	 */
	@JsonSetter("products")
	public void setProducts(final Map<String, YotpoProductData> products)
	{
		this.products = products;
	}

}
