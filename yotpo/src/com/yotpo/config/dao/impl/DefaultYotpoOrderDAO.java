package com.yotpo.config.dao.impl;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yotpo.config.dao.YotpoOrderDAO;
import com.yotpo.data.YotpoOrderSearchCriteria;
import com.yotpo.data.YotpoOrderSearchResult;
import com.yotpo.data.YotpoPagingModel;


/**
 * @author Haseeb Ahmad
 *
 */
public class DefaultYotpoOrderDAO implements YotpoOrderDAO
{

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	private static final String queryString = "SELECT {o:" + OrderModel.PK + "} FROM {" + OrderModel._TYPECODE + " AS o} "
			+ "WHERE " + "{o:" + OrderModel.CREATIONTIME + "}>=?lastFeedExecutionTime AND {o:" + OrderModel.STATUS
			+ "} in (?statuses) AND {o:" + OrderModel.SITE + "}=?site ORDER BY {o:" + OrderModel.PK + "} ASC";

	@Override
	public YotpoOrderSearchResult findOrderByStatus(final YotpoOrderSearchCriteria yotpoOrderSearchCriteria)
	{


		final List<OrderStatus> statuses = yotpoOrderSearchCriteria.getStatuses();
		final CMSSiteModel site = yotpoOrderSearchCriteria.getSite();
		final Date dateTime = yotpoOrderSearchCriteria.getDateTime();
		final YotpoPagingModel pagingModel = yotpoOrderSearchCriteria.getPagingModel();

		final YotpoOrderSearchResult yotpoOrderSearchResult = new YotpoOrderSearchResult();

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);
		query.addQueryParameter("site", site);
		query.addQueryParameter("lastFeedExecutionTime", dateTime);
		query.addQueryParameter("statuses", statuses);

		query.setCount(pagingModel.getBatchSize());
		query.setNeedTotal(true);
		query.setStart(pagingModel.getIndex());


		final SearchResult<OrderModel> searchResult = flexibleSearchService.search(query);

		final List<OrderModel> results = searchResult.getResult();

		pagingModel.setTotal(searchResult.getTotalCount());

		yotpoOrderSearchResult.setOrders(results);
		yotpoOrderSearchResult.setPagingModel(pagingModel);

		return yotpoOrderSearchResult;
	}

}
