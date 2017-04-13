package com.yotpo.config.dao;

import com.yotpo.data.YotpoOrderSearchCriteriaData;
import com.yotpo.data.YotpoOrderSearchResultData;


/**
 * @author Haseeb Ahmad
 *
 */
public interface YotpoOrderDAO
{
	/**
	 * Find orders by given search criteria and returns yotpo search results.
	 *
	 * @return YotpoOrderSearchResult
	 */
	public YotpoOrderSearchResultData findOrderByStatus(YotpoOrderSearchCriteriaData yotpoOrderSearchCriteria);
}
