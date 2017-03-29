/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Mar 29, 2017 1:06:53 PM                     ---
 * ----------------------------------------------------------------
 */
package com.yotpo.service.config;

import com.yotpo.constants.YotpoConstants;
import de.hybris.platform.cms2.jalo.site.CMSSite;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.c2l.Country;
import de.hybris.platform.jalo.c2l.Language;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.yotpo.service.config.YotpoConfig Yotpo}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedYotpoConfig extends GenericItem
{
	/** Qualifier of the <code>Yotpo.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>Yotpo.site</code> attribute **/
	public static final String SITE = "site";
	/** Qualifier of the <code>Yotpo.country</code> attribute **/
	public static final String COUNTRY = "country";
	/** Qualifier of the <code>Yotpo.language</code> attribute **/
	public static final String LANGUAGE = "language";
	/** Qualifier of the <code>Yotpo.appKey</code> attribute **/
	public static final String APPKEY = "appKey";
	/** Qualifier of the <code>Yotpo.clientSecretKey</code> attribute **/
	public static final String CLIENTSECRETKEY = "clientSecretKey";
	/** Qualifier of the <code>Yotpo.utokenAuthCode</code> attribute **/
	public static final String UTOKENAUTHCODE = "utokenAuthCode";
	/** Qualifier of the <code>Yotpo.enablePurchaseFeed</code> attribute **/
	public static final String ENABLEPURCHASEFEED = "enablePurchaseFeed";
	/** Qualifier of the <code>Yotpo.enableBottomLine</code> attribute **/
	public static final String ENABLEBOTTOMLINE = "enableBottomLine";
	/** Qualifier of the <code>Yotpo.enableReviews</code> attribute **/
	public static final String ENABLEREVIEWS = "enableReviews";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(SITE, AttributeMode.INITIAL);
		tmp.put(COUNTRY, AttributeMode.INITIAL);
		tmp.put(LANGUAGE, AttributeMode.INITIAL);
		tmp.put(APPKEY, AttributeMode.INITIAL);
		tmp.put(CLIENTSECRETKEY, AttributeMode.INITIAL);
		tmp.put(UTOKENAUTHCODE, AttributeMode.INITIAL);
		tmp.put(ENABLEPURCHASEFEED, AttributeMode.INITIAL);
		tmp.put(ENABLEBOTTOMLINE, AttributeMode.INITIAL);
		tmp.put(ENABLEREVIEWS, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.appKey</code> attribute.
	 * @return the appKey
	 */
	public String getAppKey(final SessionContext ctx)
	{
		return (String)getProperty( ctx, APPKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.appKey</code> attribute.
	 * @return the appKey
	 */
	public String getAppKey()
	{
		return getAppKey( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.appKey</code> attribute. 
	 * @param value the appKey
	 */
	public void setAppKey(final SessionContext ctx, final String value)
	{
		setProperty(ctx, APPKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.appKey</code> attribute. 
	 * @param value the appKey
	 */
	public void setAppKey(final String value)
	{
		setAppKey( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.clientSecretKey</code> attribute.
	 * @return the clientSecretKey
	 */
	public String getClientSecretKey(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CLIENTSECRETKEY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.clientSecretKey</code> attribute.
	 * @return the clientSecretKey
	 */
	public String getClientSecretKey()
	{
		return getClientSecretKey( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.clientSecretKey</code> attribute. 
	 * @param value the clientSecretKey
	 */
	public void setClientSecretKey(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CLIENTSECRETKEY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.clientSecretKey</code> attribute. 
	 * @param value the clientSecretKey
	 */
	public void setClientSecretKey(final String value)
	{
		setClientSecretKey( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, CODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, CODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.country</code> attribute.
	 * @return the country
	 */
	public Country getCountry(final SessionContext ctx)
	{
		return (Country)getProperty( ctx, COUNTRY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.country</code> attribute.
	 * @return the country
	 */
	public Country getCountry()
	{
		return getCountry( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.country</code> attribute. 
	 * @param value the country
	 */
	public void setCountry(final SessionContext ctx, final Country value)
	{
		setProperty(ctx, COUNTRY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.country</code> attribute. 
	 * @param value the country
	 */
	public void setCountry(final Country value)
	{
		setCountry( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableBottomLine</code> attribute.
	 * @return the enableBottomLine
	 */
	public Boolean isEnableBottomLine(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLEBOTTOMLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableBottomLine</code> attribute.
	 * @return the enableBottomLine
	 */
	public Boolean isEnableBottomLine()
	{
		return isEnableBottomLine( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableBottomLine</code> attribute. 
	 * @return the enableBottomLine
	 */
	public boolean isEnableBottomLineAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnableBottomLine( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableBottomLine</code> attribute. 
	 * @return the enableBottomLine
	 */
	public boolean isEnableBottomLineAsPrimitive()
	{
		return isEnableBottomLineAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableBottomLine</code> attribute. 
	 * @param value the enableBottomLine
	 */
	public void setEnableBottomLine(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLEBOTTOMLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableBottomLine</code> attribute. 
	 * @param value the enableBottomLine
	 */
	public void setEnableBottomLine(final Boolean value)
	{
		setEnableBottomLine( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableBottomLine</code> attribute. 
	 * @param value the enableBottomLine
	 */
	public void setEnableBottomLine(final SessionContext ctx, final boolean value)
	{
		setEnableBottomLine( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableBottomLine</code> attribute. 
	 * @param value the enableBottomLine
	 */
	public void setEnableBottomLine(final boolean value)
	{
		setEnableBottomLine( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enablePurchaseFeed</code> attribute.
	 * @return the enablePurchaseFeed
	 */
	public Boolean isEnablePurchaseFeed(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLEPURCHASEFEED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enablePurchaseFeed</code> attribute.
	 * @return the enablePurchaseFeed
	 */
	public Boolean isEnablePurchaseFeed()
	{
		return isEnablePurchaseFeed( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enablePurchaseFeed</code> attribute. 
	 * @return the enablePurchaseFeed
	 */
	public boolean isEnablePurchaseFeedAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnablePurchaseFeed( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enablePurchaseFeed</code> attribute. 
	 * @return the enablePurchaseFeed
	 */
	public boolean isEnablePurchaseFeedAsPrimitive()
	{
		return isEnablePurchaseFeedAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enablePurchaseFeed</code> attribute. 
	 * @param value the enablePurchaseFeed
	 */
	public void setEnablePurchaseFeed(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLEPURCHASEFEED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enablePurchaseFeed</code> attribute. 
	 * @param value the enablePurchaseFeed
	 */
	public void setEnablePurchaseFeed(final Boolean value)
	{
		setEnablePurchaseFeed( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enablePurchaseFeed</code> attribute. 
	 * @param value the enablePurchaseFeed
	 */
	public void setEnablePurchaseFeed(final SessionContext ctx, final boolean value)
	{
		setEnablePurchaseFeed( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enablePurchaseFeed</code> attribute. 
	 * @param value the enablePurchaseFeed
	 */
	public void setEnablePurchaseFeed(final boolean value)
	{
		setEnablePurchaseFeed( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableReviews</code> attribute.
	 * @return the enableReviews
	 */
	public Boolean isEnableReviews(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, ENABLEREVIEWS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableReviews</code> attribute.
	 * @return the enableReviews
	 */
	public Boolean isEnableReviews()
	{
		return isEnableReviews( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableReviews</code> attribute. 
	 * @return the enableReviews
	 */
	public boolean isEnableReviewsAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isEnableReviews( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.enableReviews</code> attribute. 
	 * @return the enableReviews
	 */
	public boolean isEnableReviewsAsPrimitive()
	{
		return isEnableReviewsAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableReviews</code> attribute. 
	 * @param value the enableReviews
	 */
	public void setEnableReviews(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, ENABLEREVIEWS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableReviews</code> attribute. 
	 * @param value the enableReviews
	 */
	public void setEnableReviews(final Boolean value)
	{
		setEnableReviews( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableReviews</code> attribute. 
	 * @param value the enableReviews
	 */
	public void setEnableReviews(final SessionContext ctx, final boolean value)
	{
		setEnableReviews( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.enableReviews</code> attribute. 
	 * @param value the enableReviews
	 */
	public void setEnableReviews(final boolean value)
	{
		setEnableReviews( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.language</code> attribute.
	 * @return the language
	 */
	public Language getLanguage(final SessionContext ctx)
	{
		return (Language)getProperty( ctx, LANGUAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.language</code> attribute.
	 * @return the language
	 */
	public Language getLanguage()
	{
		return getLanguage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.language</code> attribute. 
	 * @param value the language
	 */
	public void setLanguage(final SessionContext ctx, final Language value)
	{
		setProperty(ctx, LANGUAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.language</code> attribute. 
	 * @param value the language
	 */
	public void setLanguage(final Language value)
	{
		setLanguage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.site</code> attribute.
	 * @return the site
	 */
	public CMSSite getSite(final SessionContext ctx)
	{
		return (CMSSite)getProperty( ctx, SITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.site</code> attribute.
	 * @return the site
	 */
	public CMSSite getSite()
	{
		return getSite( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.site</code> attribute. 
	 * @param value the site
	 */
	public void setSite(final SessionContext ctx, final CMSSite value)
	{
		setProperty(ctx, SITE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.site</code> attribute. 
	 * @param value the site
	 */
	public void setSite(final CMSSite value)
	{
		setSite( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.utokenAuthCode</code> attribute.
	 * @return the utokenAuthCode
	 */
	public String getUtokenAuthCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, UTOKENAUTHCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Yotpo.utokenAuthCode</code> attribute.
	 * @return the utokenAuthCode
	 */
	public String getUtokenAuthCode()
	{
		return getUtokenAuthCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.utokenAuthCode</code> attribute. 
	 * @param value the utokenAuthCode
	 */
	public void setUtokenAuthCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, UTOKENAUTHCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Yotpo.utokenAuthCode</code> attribute. 
	 * @param value the utokenAuthCode
	 */
	public void setUtokenAuthCode(final String value)
	{
		setUtokenAuthCode( getSession().getSessionContext(), value );
	}
	
}
