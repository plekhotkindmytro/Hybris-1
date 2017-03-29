package com.yotpo.config.service;

import de.hybris.platform.cms2.model.site.CMSSiteModel;

import com.yotpo.model.service.config.YotpoModel;



/**
 * Created by vijay on 07/02/2017.
 */
public interface YotpoConfigurationService
{
	/**
	 * returns the yotpo configurations for given cms site.
	 * 
	 * @return YotpoModel
	 */
	YotpoModel getYotpoConfiguration(CMSSiteModel cmsSiteModel);
}
