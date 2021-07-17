package com.example.quartz.model;

import lombok.Data;
import org.quartz.JobDataMap;

@Data
public class JobRequest {
	private String jobName;
	private String jobGroup;
	private String triggerName;
	private String triggerGroup;
	private String cronExpression;
}
