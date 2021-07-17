package com.example.rest.mtest;

import com.example.rest.model.Model;
import com.example.rest.model.T1;
import com.example.rest.model.T2;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
public class MTest {

	@Test
	public void listTest(){
		T1 t1 = new T1();
		t1.setA("a");
		t1.setB("B");
		t1.setList(Arrays.asList("a","b"));
		List<T1> t1List = Arrays.asList(t1);

		List<T2> t2List = new ArrayList<T2>();
		t2List = CollectionUtils.transform(t1List, (Transformer) t2List);
		log.info("Res {}", t2List);

	}

	@Test
	public void testCnt(){
		List<Integer> list = Arrays.asList(1,2,3,4,5);


	}


}
