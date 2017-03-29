package com.yotpo.config.dao;

import com.yotpo.data.YotpoOrderSearchCriteria;
import com.yotpo.data.YotpoOrderSearchResult;


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
	public YotpoOrderSearchResult findOrderByStatus(YotpoOrderSearchCriteria yotpoOrderSearchCriteria);
}
