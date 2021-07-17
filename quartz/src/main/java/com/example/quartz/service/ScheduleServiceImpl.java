package com.example.quartz.service;

import com.example.quartz.model.JobRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements  ScheduleService {

	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;

	@Override
	public Trigger createCronTrigger(JobRequest req) {
		return TriggerBuilder
				.newTrigger()
				.withIdentity(req.getTriggerName(), req.getTriggerGroup())
				.withSchedule(CronScheduleBuilder.cronSchedule(req.getCronExpression()))
				.build();
	}

	@Override
	public Trigger createSimpleTrigger(JobRequest req) {
		return TriggerBuilder
				.newTrigger()
				.withIdentity(req.getTriggerName(), req.getTriggerGroup())
				.withSchedule(SimpleScheduleBuilder
						.simpleSchedule()
						.withIntervalInSeconds(30)
						// TODO 리피트 안돌고 수동으로 한번만 작동되게 찾아보기
						.repeatForever())
				.build();
	}

	@Override
	public JobDetail createJobDetail(JobRequest jobRequest, Class<? extends Job> jobClass) {
		return JobBuilder
				.newJob()
				.storeDurably()
				.withIdentity(jobRequest.getJobName(), jobRequest.getJobGroup())
				.build();
	}

	@Override
	public boolean createJob(JobRequest jobRequest, Class<? extends Job> jobClass) {
		try{
			// 초기화되지 않은 Std스케줄러 팩토리 생성
			schedulerFactory = new StdSchedulerFactory();
			// 스케줄러에 대한 클라이언트 가능한 핸들 반환
			scheduler = schedulerFactory.getScheduler();

			// Job 등록
			JobDetail jobDetail = createJobDetail(jobRequest, jobClass);

			// Trigger 생성
			Trigger trigger = null;
			if(jobRequest.getCronExpression() == null){
				trigger = createSimpleTrigger(jobRequest);
			}else {
				trigger = createCronTrigger(jobRequest);
			}

			// Binding
			scheduler.scheduleJob(jobDetail, trigger);

			return true;
		} catch (Exception e) {
			log.error("Error {}", e);
			return false;
		}
	}


	@Override
	// TODO 강제종료?? 찾아보기
	public boolean deleteJob(TriggerKey triggerKey, JobKey jobKey) {
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			scheduler.pauseTrigger(triggerKey); // stop trigger
			scheduler.unscheduleJob(triggerKey); // remove trigger
			scheduler.deleteJob(jobKey); // remove job
			return true;
		} catch (Exception e) {
			log.error("Error {}", e);
			return false;
		}
	}

	@Override
	public boolean updateJob(String jobName, String time) {
		return false;
	}


	@Override
	@PostConstruct
	public void startJob() {
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			scheduler.start();
			log.info("scheduler start!!");
		} catch (Exception e) {
			log.error("Error {}", e);
		}
	}

	@Override
	public void stopJob() {
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
			// 스케줄러와 관련된 모든 자원 정리
			scheduler.shutdown();
		} catch (Exception e) {
			log.error("Error {}", e);
		}
	}
}
