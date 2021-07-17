package com.example.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class SimpleJob extends QuartzJobBean {
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		int jobId = jobDataMap.getInt("jobId");
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("============================================================================");
		log.info("SimpleJob started :: jobId : {} jobKey : {} ", jobId, jobKey);
	}
}
