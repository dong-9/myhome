package com.example.rest.controller;

import com.example.rest.model.Element;
import com.example.rest.model.ElementList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@Api(value = "")
public class SwaggerController {

	/*
		ApiOperation
			value = 메소드 설명1
			notes = 메소드 설명2
	 */


	@GetMapping(value = "/get", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public String get(Element element){
		log.info("Element : {}", element);
		return "";
	}

//	@ApiOperation(value = "덧셈", notes = "num1 과 num2 를 더합니다.")
//	@GetMapping(value = "/add")
//	public Integer addition() {
//		log.info("asd");
//		return 1;
//	}
//
//	@ApiOperation(value = "뺄셈", notes = "num1 에서 num2 를 뺍니다.")
//	@PostMapping(value = "/sub")
//	public Integer subtraction(Integer num1, Integer num2) {
//		return num1 - num2;
//	}
//
//	@ApiOperation(value = "곱셈", notes = "num1 에 num2 를 곱합니다.")
//	@PutMapping(value = "/mul")
//	public Integer multiplication(Integer num1, Integer num2) {
//		return num1 * num2;
//	}
//
//	@ApiOperation(value = "나눗셈", notes = "num1 에서 num2 로 나눕니다.")
//	@DeleteMapping(value = "/div")
//	public Integer division(Integer num1, Integer num2) {
//		return num1 / num2;
//	}
}
