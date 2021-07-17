package com.example.publisher.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements SendMessageService{

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void sendMail(String exchangeName, String routingKey, String message) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
	}
}
