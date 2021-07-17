package com.example.rest.model;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CaclNum {
	/*
		@ApiParam = 멤버변수에 대한 설명 및 다양한 설정 지원
			value = 저장되어야 할 값에 설명을 명시
			required = 필수 여부를 지정
	 */

	@ApiParam(value = "숫자1", required = true)
	private int num1;

	@ApiParam(value = "숫자2", required = true)
	private int num2;

	private String a;
}
