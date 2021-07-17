package com.example.consumer.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfig {

	// TODO 프로퍼티 파일에 넣기
	@Value("${exchange.name}")
	private String EXCHANGE_NAME;
	@Value("${queue.name}")
	private String QUEUE_NAME;
	@Value("${routing.key}")
	private String ROUTING_KEY;

	@Bean
	TopicExchange exchange(){
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	Queue queue(){
		return new Queue(QUEUE_NAME);
	}

	/*
		Producer가 보낸 메시지를 Exchange에서
		Exchange 이름과 Routing Key를 가지고 Queue를 바인딩
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	/*
		ConnectionFactory : RabbitMQ를 연결하고 관리하는 인터페이스
		RabbitTemplate : 메시지 처리?를 위한 템플릿
	 */
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter){
		// RabbitMQ 연결 (프로퍼티 파일 정보가지고)
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		// 메시지 변환기 설정
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	@Bean
	MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}
}
