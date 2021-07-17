package com.example.publisher.controller;

import com.example.publisher.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SendMessageController {

	@Autowired
	private SendMessageService sendMessageService;

	@GetMapping(value = "/send")
	public String sendMessage(String message){
		try {
			sendMessageService.sendMail(
					"my-exchange-test2",
					"my-routing-key-test3",
					message
			);
			return "ok";
		} catch (Exception e) {
			log.error("Error {}", e);
			return "fail";
		}
	}
}
