package com.example.rest.mtest;

import com.example.rest.model.CaclNum;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
public class PreconditonsTest {

	static int cnt = 0;

	@Test
	public void checkNotNull(){
//		CaclNum a = new CaclNum();
//		Preconditions.checkNotNull(a.getA(), "null");
		log.info("asd");
	}

	@Test
	public void asyncTest(){
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("a");
		Optional<String> res = list.stream().filter(c -> Objects.equals("a", c)).findFirst();
		if(res.isPresent()){

			res.stream().forEach(System.out::println);
		}
	}

	@Test
	public void fileTest(){
		// /Users/jdh/Desktop
		String myPath = "/Users/jdh/Desktop/TestFolder/test.txt";
		File imageTempFile = new File(myPath);
		// 파일 삭제
		//FileUtils.forceDelete(new File(localTempPath));
	}

	public int ct(String name) {
		int sum = 0;
		for(int i = 0; i < 10; i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " " + (i+1) + " 초");
			sum += i+1;
		}
		return sum;
	}

	@Test
	public void a() throws Exception{
		Executor executor1 = Executors.newSingleThreadExecutor();
		Executor executor2 = Executors.newSingleThreadExecutor();
		Executor executor3 = Executors.newSingleThreadExecutor();
		CompletableFuture<Integer> a =  CompletableFuture.supplyAsync(() -> ct("First Thread"), executor1)
//														 .orTimeout(9, TimeUnit.SECONDS)
														 .handle((Integer, throwable) -> {
															 System.out.println("First Thread Result : " + Integer);
															 System.out.println("First Thread Exception : " + throwable);
															 return Integer;
														 }
		);
//		CompletableFuture<Void> d = a.thenAccept(c -> System.out.println("what "+ c));
		CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> ct("Second Thread"), executor2);
		CompletableFuture<Integer> c = CompletableFuture.supplyAsync(() -> ct("Third Thread"), executor3);

		System.out.println("a.get() : " + a.get());
		System.out.println("b.get() : " + b.get());
		System.out.println("c.get() : " + c.get());
	}

	@Test
	public void dateTest(){

	}

	@Test
	public void listTest(){
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
		List<Integer> list = Lists.newArrayList();
		for(int i = 0; i < queue.size(); i++){
			try {
				list.add(queue.take());
			} catch (Exception e){

			}
		}
		log.info("list {}", list);
	}

	@Test
	public void printTest(){
		TT.a();
	}
}

enum TT {
	ON("on1", "on2", "on3", "on4"),
	OFF("off1", "off2", "off3", "off4");
	String on1;
	String on2;
	String on3;
	String on4;
	TT(String on1, String on2, String on3, String on4) {
		this.on1 = on1;
		this.on2 = on2;
		this.on3 = on3;
		this.on4 = on4;
	}

	static void a() {
		TT[] a = values();
		for(TT li : a){
			System.out.println(li);
		}
	}

}

