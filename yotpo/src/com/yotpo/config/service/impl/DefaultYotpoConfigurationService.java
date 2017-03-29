package com.yotpo.config.service.impl;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.yotpo.config.dao.YotpoConfigurationDAO;
import com.yotpo.config.service.YotpoConfigurationService;
import com.yotpo.model.service.config.YotpoModel;



/**
 * Created by vijay on 07/02/2017.
 */
public class DefaultYotpoConfigurationService implements YotpoConfigurationService
{

	@Resource
	private YotpoConfigurationDAO yotpoConfigurationDAO;

	@Override
	public YotpoModel getYotpoConfiguration(final CMSSiteModel cmsSiteModel)
			throws AmbiguousIdentifierException, UnknownIdentifierException
	{
		final List<YotpoModel> result = yotpoConfigurationDAO.findAllByCMSSite(cmsSiteModel);

		if (CollectionUtils.isEmpty(result))
		{
			throw new UnknownIdentifierException("YotpoConfiguration for site '" + cmsSiteModel.getName() + "' not found!");
		}

		return result.get(0);
	}
}
