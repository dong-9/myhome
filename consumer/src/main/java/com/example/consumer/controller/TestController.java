package com.example.consumer.controller;

import com.example.consumer.service.TestService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@PostMapping(value = "/test")
	public String test(Integer cnt){
		rabbitTemplate.convertAndSend(
				"my-direct",
				"my.direct.queue",
				cnt);
		return "su";
	}
}
