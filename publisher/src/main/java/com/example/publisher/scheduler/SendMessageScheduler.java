package com.example.publisher.scheduler;

import com.example.publisher.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@Slf4j
// TODO 스케줄러 -> 쿼츠로 변경
// TODO 수동으로 메시지 보내기 추가
public class SendMessageScheduler {

	@Autowired
	private SendMessageService sendMessageService;

	/*
		Exchange Name = my-exchange-test1
		Routing Key = my-routing-key-test1
	 */
//	@Scheduled(cron = "1/30 * * * * *")
	public void sendMail1(){
		log.info("send mail1");
		sendMessageService.sendMail(
				"my-exchange-test1",
				"my.routing.key1",
				"scheduler Message1");
	}

	/*
		Exchange Name = my-exchange-test1
		Routing Key = my-routing-key-test2
	 */
//	@Scheduled(cron = "1/30 * * * * *")
	public void sendMail2(){
		log.info("send mail2");
		sendMessageService.sendMail(
				"my-exchange-test1",
				"my.routing.key2",
				"scheduler Message2");
	}

	/*
		Exchange Name = my-exchange-test2
		Routing Key = my-routing-key-test3
	 */
//	@Scheduled(cron = "1/30 * * * * *")
	public void sendMail3(){
		log.info("send mail3");

		/*
			JAVA객체를 AMQP로 변환하고
			메시지를 라우트 키와 특정 exchage로 보냄
		 */
		sendMessageService.sendMail(
				"my-exchange-test2",
				"my.routing.key3",
				"scheduler Message3");
	}
}