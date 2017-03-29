package com.yotpo.cronjob;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.Resource;

import com.yotpo.event.YotpoExportDataEvent;
import com.yotpo.model.order.dataexport.YotpoOrderExportCronJobModel;



/**
 * Created by vijay on 08/02/2017.
 */
public class YotpoOrderExportJobPerformable extends AbstractJobPerformable<YotpoOrderExportCronJobModel>
{

	@Resource
	private EventService eventService;
	@Resource
	private ModelService modelService;

	@Override
	public PerformResult perform(final YotpoOrderExportCronJobModel cronJob)
	{
		final YotpoExportDataEvent event = new YotpoExportDataEvent();

		populateEvent(cronJob, event);
		eventService.publishEvent(event);

		cronJob.setPreviousJobStartTime(event.getJobStartTime());
		modelService.save(cronJob);

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * populate the yotpo export data event
	 */
	protected void populateEvent(final YotpoOrderExportCronJobModel cronJob, final YotpoExportDataEvent event)
	{
		event.setCode(cronJob.getCode());
		event.setSite(cronJob.getSite());

		final Date previousStartTime = cronJob.getPreviousJobStartTime() != null ? cronJob.getPreviousJobStartTime()
				: Date.from(LocalDate.of(2017, Month.JANUARY, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

		event.setPreviousJobStartTime(previousStartTime);

	}
}
