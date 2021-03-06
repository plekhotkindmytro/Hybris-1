package com.yotpo.yotpoaddon.view;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeViewHandler;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.yotpo.builder.YotpoCategoryPathBuilder;


/**
 *
 * @author Haseeb Ahmad
 *
 */
public class YotpoBottomlineBeforeViewHandler implements BeforeViewHandler
{

	private final static Logger LOG = Logger.getLogger(YotpoBottomlineBeforeViewHandler.class);

	@Resource
	private YotpoCategoryPathBuilder yotpoCategoryPathBuilder;

	private Set<String> pathsToIntercept;

	private Set<String> requestMethods;

	@Override
	public void beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelAndView modelAndView)
	{
		final String path = new UrlPathHelper().getServletPath(request);

		if (shouldIntercept(path) && requestMethodMatches(request.getMethod()))
		{

			final boolean isBottomLineEnabled = (boolean) modelAndView.getModel().get("isBottomLineEnabled");
			if (isBottomLineEnabled)
			{
				final ProductData productData = (ProductData) modelAndView.getModel().get("product");
				modelAndView.addObject("categoryPath", yotpoCategoryPathBuilder.buildCategoryPath(productData.getCode()));
			}
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
	 * @param pathsToIntercept
	 *           the pathsToIntercept to set
	 */
	@Required
	public void setPathsToIntercept(final Set<String> pathsToIntercept)
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
	 * @param requestMethods
	 *           the requestMethods to set
	 */
	@Required
	public void setRequestMethods(final Set<String> requestMethods)
	{
		this.requestMethods = requestMethods;
	}
}
