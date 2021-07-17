package com.example.publisher.quartz;

import com.example.publisher.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@Configuration
public class SendMessageJob1 extends QuartzJobBean {

	@Autowired
	private SendMessageService sendMessageService;


	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		log.info("Send Message Service");
		log.info("Key {}", context.getJobDetail().getKey());
		log.info("ex {}",jobDataMap.get("exchangeName"));
		log.info("key {}",jobDataMap.get("routingKey"));
		log.info("message {}",jobDataMap.get("message"));
//		sendMessageService.sendMail(
//				"my-exchange-test1",
//				"my.routing.key1",
//				"scheduler Message1");
	}
}
