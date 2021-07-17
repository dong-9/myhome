package com.example.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestService {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void sendMessage(Integer cnt){
		log.info("Cnt = {}", cnt++);
		if(cnt == 5) return;
		rabbitTemplate.convertAndSend(
				"my-direct",
				"my.direct.queue",
				cnt);
	}
}
