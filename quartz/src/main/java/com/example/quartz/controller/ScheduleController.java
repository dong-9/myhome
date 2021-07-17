package com.example.quartz.controller;

import com.example.quartz.job.CronJob;
import com.example.quartz.model.JobRequest;
import com.example.quartz.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@PostMapping(value = "/addJob")
	public String addJob(JobRequest jobRequest){
		log.info("jobRequest {}", jobRequest);
		if(scheduleService.createJob(jobRequest, CronJob.class)){
			return "ok";
		}
		return "false";
	}

	@PostMapping(value = "/start")
	public String start(){
		scheduleService.startJob();
		return "start";
	}

	@PostMapping(value = "/stop")
	public String stop(){
		scheduleService.stopJob();
		return "stop";
	}

	@DeleteMapping(value = "/delete")
	public String delete(String trigger, String job){
		TriggerKey triggerKey = TriggerKey.triggerKey(trigger);
		JobKey jobKey = JobKey.jobKey(job);
		log.info("triggerKey {}, jobKey {}", triggerKey, jobKey);

		if(scheduleService.deleteJob(TriggerKey.triggerKey(job),JobKey.jobKey(job))){
			return "ok";
		}
		return "false";
	}

//	@PostMapping(value = "/mapTest")
//	public Object test(JobRequest jobRequest){
//		JobDataMap jobDataMap = jobRequest.getJobDataMap();
//		log.info("Wrapped {}",jobDataMap.getWrappedMap());
//		return jobRequest.getJobDataMap();
//	}
}
