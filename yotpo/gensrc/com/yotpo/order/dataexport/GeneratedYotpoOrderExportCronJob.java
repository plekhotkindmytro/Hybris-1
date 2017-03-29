/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Mar 29, 2017 1:06:53 PM                     ---
 * ----------------------------------------------------------------
 */
package com.yotpo.order.dataexport;

import com.yotpo.constants.YotpoConstants;
import de.hybris.platform.cms2.jalo.site.CMSSite;
import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.processing.constants.ProcessingConstants;
import de.hybris.platform.util.BidirectionalOneToManyHandler;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.yotpo.order.dataexport.YotpoOrderExportCronJob YotpoOrderExportCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedYotpoOrderExportCronJob extends CronJob
{
	/** Qualifier of the <code>YotpoOrderExportCronJob.site</code> attribute **/
	public static final String SITE = "site";
	/** Qualifier of the <code>YotpoOrderExportCronJob.previousJobStartTime</code> attribute **/
	public static final String PREVIOUSJOBSTARTTIME = "previousJobStartTime";
	/**
	* {@link BidirectionalOneToManyHandler} for handling 1:n JOB's relation attributes from 'one' side.
	**/
	protected static final BidirectionalOneToManyHandler<GeneratedYotpoOrderExportCronJob> JOBHANDLER = new BidirectionalOneToManyHandler<GeneratedYotpoOrderExportCronJob>(
	ProcessingConstants.TC.SERVICELAYERJOB,
	false,
	"job",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(SITE, AttributeMode.INITIAL);
		tmp.put(PREVIOUSJOBSTARTTIME, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes) throws JaloBusinessException
	{
		JOBHANDLER.newInstance(ctx, allAttributes);
		return super.createItem( ctx, type, allAttributes );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>YotpoOrderExportCronJob.previousJobStartTime</code> attribute.
	 * @return the previousJobStartTime
	 */
	public Date getPreviousJobStartTime(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, PREVIOUSJOBSTARTTIME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>YotpoOrderExportCronJob.previousJobStartTime</code> attribute.
	 * @return the previousJobStartTime
	 */
	public Date getPreviousJobStartTime()
	{
		return getPreviousJobStartTime( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>YotpoOrderExportCronJob.previousJobStartTime</code> attribute. 
	 * @param value the previousJobStartTime
	 */
	public void setPreviousJobStartTime(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, PREVIOUSJOBSTARTTIME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>YotpoOrderExportCronJob.previousJobStartTime</code> attribute. 
	 * @param value the previousJobStartTime
	 */
	public void setPreviousJobStartTime(final Date value)
	{
		setPreviousJobStartTime( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>YotpoOrderExportCronJob.site</code> attribute.
	 * @return the site
	 */
	public CMSSite getSite(final SessionContext ctx)
	{
		return (CMSSite)getProperty( ctx, SITE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>YotpoOrderExportCronJob.site</code> attribute.
	 * @return the site
	 */
	public CMSSite getSite()
	{
		return getSite( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>YotpoOrderExportCronJob.site</code> attribute. 
	 * @param value the site
	 */
	public void setSite(final SessionContext ctx, final CMSSite value)
	{
		setProperty(ctx, SITE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>YotpoOrderExportCronJob.site</code> attribute. 
	 * @param value the site
	 */
	public void setSite(final CMSSite value)
	{
		setSite( getSession().getSessionContext(), value );
	}
	
}
