package com.example.quartz.service;

import com.example.quartz.model.JobRequest;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public interface ScheduleService {

	boolean createJob(JobRequest jobRequest, Class<? extends Job> jobClass);
	boolean deleteJob(TriggerKey triggerKey, JobKey jobKey);
	boolean updateJob(String jobName, String time);
	Trigger createCronTrigger(JobRequest jobRequest);
	Trigger createSimpleTrigger(JobRequest jobRequest);
	JobDetail createJobDetail(JobRequest jobRequest, Class<? extends Job> jobClass);
	void startJob();
	void stopJob();
}
