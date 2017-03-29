package com.yotpo.yotpoaddon.view;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import java.util.Set;

import com.yotpo.config.service.YotpoConfigurationService;
import com.yotpo.model.service.config.YotpoModel;
import com.yotpo.util.YotpoUtils;

/**
 * 
 * @author Haseeb Ahmad
 *
 */
public class YotpoHeaderBeforeViewHandler implements BeforeViewHandler
{
	private final static Logger LOG = Logger.getLogger(YotpoHeaderBeforeViewHandler.class);
	
	@Resource
	private CMSSiteService cmsSiteService;
	@Resource
	private YotpoConfigurationService yotpoConfigurationService;
	@Resource
	private ConfigurationService configurationService;
	
	private Set<String> pathsToIntercept;

	private Set<String> requestMethods;
	
	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
	{
	
		final String path = new UrlPathHelper().getServletPath(request);

      if (shouldIntercept(path) && requestMethodMatches(request.getMethod()))
      {
      	final YotpoModel yotpoConfiguretion = yotpoConfigurationService.getYotpoConfiguration(cmsSiteService.getCurrentSite());
      	final boolean isValidate = YotpoUtils.validateMandatoryAppKey(yotpoConfiguretion);
			if (!isValidate)
			{
				LOG.error("The current site missing mandatory data for App Key therefore skiping the process.");
				return;
			}

   		String yotpoWidgetURL = "";
			try
			{
				yotpoWidgetURL = (String) configurationService.getConfiguration().getProperty("yotpo.widget.url");
			}
			catch(Exception e)
			{
				LOG.error("Exception occur while loading yotpoWidgetURL property : " + e.toString());
				return;
			}
			
   		
   		final String appKey = yotpoConfiguretion.getAppKey();
   		
   		modelAndView.addObject("yotpoWidgetURL", StringUtils.replace(yotpoWidgetURL, "{APPKEY}", appKey));
      }
   }
	
	private boolean requestMethodMatches(final String method)
   {
       return requestMethods.contains(method);
   }

   private boolean shouldIntercept(final String path)
   {
       for (final String regex : pathsToIntercept)
       {
           if (path.matches(regex))
           {
               return true;
           }
       }
       return false;
   }

	/**
	 * @return the pathsToIntercept
	 */
	public Set<String> getPathsToIntercept()
	{
		return pathsToIntercept;
	}

	/**
	 * @param pathsToIntercept the pathsToIntercept to set
	 */
	@Required
	public void setPathsToIntercept(Set<String> pathsToIntercept)
	{
		this.pathsToIntercept = pathsToIntercept;
	}
	
	/**
	 * @return the requestMethods
	 */
	public Set<String> getRequestMethods()
	{
		return requestMethods;
	}

	/**
	 * @param requestMethods the requestMethods to set
	 */
	@Required
	public void setRequestMethods(Set<String> requestMethods)
	{
		this.requestMethods = requestMethods;
	}
}