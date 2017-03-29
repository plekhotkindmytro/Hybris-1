package com.yotpo.config.dao;

import de.hybris.platform.cms2.model.site.CMSSiteModel;

import java.util.List;

import com.yotpo.model.service.config.YotpoModel;


/**
 * Created by vijay on 07/02/2017.
 */
public interface YotpoConfigurationDAO
{
	/**
	 * returns yotpo configuration as per cms site
	 *
	 * @return List<YotpoModel>
	 */
	List<YotpoModel> findAllByCMSSite(CMSSiteModel cmsSiteModel);
}
