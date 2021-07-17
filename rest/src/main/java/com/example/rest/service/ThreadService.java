package com.example.rest.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
public class ThreadService {
	public static void main(String[] args){
		Thread t1 = new Thread(new ThreadService1());
		Thread t2 = new Thread(new ThreadService1());

		t1.start();
		t2.start();

	}
}

@Slf4j
class ThreadService1 implements Runnable {

	static int money = 10000;

	@SneakyThrows
	@Override
	public void run() {
		while(money != 0){
			minMoney();
		}
	}

	public synchronized void minMoney(){
		log.info("money {}", money);
		money -= 500;
	}
}
