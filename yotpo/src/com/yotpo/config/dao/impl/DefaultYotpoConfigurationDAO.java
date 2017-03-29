package com.yotpo.config.dao.impl;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yotpo.config.dao.YotpoConfigurationDAO;
import com.yotpo.model.service.config.YotpoModel;


/**
 * Created by vijay on 07/02/2017.
 */
public class DefaultYotpoConfigurationDAO implements YotpoConfigurationDAO
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	private static final String queryString = "SELECT {y:" + YotpoModel.PK + "} FROM {" + YotpoModel._TYPECODE + " AS y} WHERE {y:"
			+ YotpoModel.SITE + "}=?cmsSite";

	@Override
	public List<YotpoModel> findAllByCMSSite(final CMSSiteModel cmsSiteModel)
	{

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("cmsSite", cmsSiteModel);
		return flexibleSearchService.<YotpoModel> search(query).getResult();
	}
}
