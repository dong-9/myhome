package com.example.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class CronJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JobKey jobKey = context.getJobDetail().getKey();
		TriggerKey triggerKey = context.getTrigger().getKey();

		log.info("============================================================================");
		log.info("CronJob started :: jobKey : {} triggerKey : {}", jobKey, triggerKey);
	}
}
