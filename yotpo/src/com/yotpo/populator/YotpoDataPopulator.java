package com.yotpo.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.yotpo.data.YotpoOrderData;
import com.yotpo.mapper.YotpoFeedDataMapper;


/**
 * Created by haseeb
 */
public class YotpoDataPopulator implements Populator<OrderModel, YotpoOrderData>
{

	private static final Logger LOG = Logger.getLogger(YotpoDataPopulator.class);

	@Resource
	private YotpoFeedDataMapper yotpoFeedMapper;

	@Override
	public void populate(final OrderModel source, final YotpoOrderData target)
	{
		final OrderModel order = source;
		if (source != null)
		{
			try
			{
				yotpoFeedMapper.extractYotpoOrderData(order, target);
			}
			catch (final Exception e)
			{
				LOG.error("Error during mapping a order model", e);
				throw new UnknownIdentifierException("Error during mapping a model : " + e);
			}
		}
	}
}
