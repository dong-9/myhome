package com.example.publisher.quartz;

import lombok.RequiredArgsConstructor;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {

	private Scheduler scheduler;

	// 인스턴스화 하자마자 메소드 자동실행
	@PostConstruct
	public void start() throws SchedulerException {
		// 스케줄러 생성
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		scheduler = schedulerFactory.getScheduler();

		// 스케줄러 시작
		scheduler.start();

		// Job Data 생성
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("exchangeName","my-exchange-test1");
		jobDataMap.put("routingKey","my.routing.key1");
		jobDataMap.put("message","quartz scheduler message1");

		createSimpleTrigger(createJob(jobDataMap, SendMessageJob1.class));
		// 스케줄러에 Job과 Trigger 연결
//		scheduler.scheduleJob(jobDetail, trigger);
	}

	private JobDetail createJob(JobDataMap jobDataMap, Class<? extends Job> jobClass){
		return JobBuilder
				.newJob(jobClass)
				.withIdentity("sendmessagejob1", "group1")
				.setJobData(jobDataMap)
				.build();
	}

	private Trigger createSimpleTrigger(JobDetail jobDetail){
		return TriggerBuilder
				.newTrigger()
				.forJob(jobDetail)
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(5)
						.repeatForever())
				.build();
	}

	private Trigger createCronTrigger(JobDetail jobDetail){
		return TriggerBuilder
				.newTrigger()
				.forJob(jobDetail)
				.withSchedule(CronScheduleBuilder.cronSchedule("1/10 * * * * *"))
				.build();
	}
}