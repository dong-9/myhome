package com.example.consumer.rabbitmq;

import com.example.consumer.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SampleListener {

	@Autowired
	TestService testService;

	/*
		Queue Name 기반으로 메시지를 받음

		메시지를 받는 방법
		1. RabbitTemplate을 사용해 큐로 부터 메시지를 가져옴
			=> rabbitTemplate.receiveAndConvert("queueName")
		2. @RabbitListener를 이용해 지정된 메서드로 메시지가 푸시됨
	 */
	@RabbitListener(queues = "my-queue-test1")
	public void receiveMessage1(final Message message) {
		log.info("Listener Message Order1 {}", message.toString());
	}

	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange(name = "my-exchange-test1", type = ExchangeTypes.TOPIC),
			value = @Queue(name = "my-queue-test2"),
			key = "my.routing.key2"
	))
	public void receiveMessage2(final Message message){
		log.info("Listener Message Order2 {}", message.toString());
	}

	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange(name = "my-exchange-test2", type = ExchangeTypes.TOPIC),
			value = @Queue(name = "my-queue-test3"),
			key = "my.routing.key3"
	))
	public void receiveMessage3(final Message message){
		log.info("Listener Message Order3 {}", message.toString());
	}

	@RabbitListener(bindings = @QueueBinding(
			exchange = @Exchange(name = "my-direct", type = ExchangeTypes.DIRECT),
			value = @Queue(name = "my-direct-queue"),
			key = "my.direct.queue"
	))
	public void testReceiveMessage(Integer cnt){
		log.info("리스너 카운트 {}", cnt);
		testService.sendMessage(cnt);
	}
}