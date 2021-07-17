package com.example.publisher.quartz;

import com.example.publisher.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SendMessageJob2 implements Job {

	@Autowired
	private SendMessageService sendMessageService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("Send Message Service");
		sendMessageService.sendMail(
				"exchangeName",
				"routingKey",
				"message"
				);
	}

}
