package com.example.publisher.service;

public interface SendMessageService {
	void sendMail(String exchangeName, String routingKey, String message);
}
