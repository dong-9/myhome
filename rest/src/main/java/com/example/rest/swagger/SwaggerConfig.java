package com.example.rest.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/*
		Docket = api의 그룹명, 이동경로, 보여질 api가 속한 패키지 등의 자세한 정보를 담음
		Consumes, Produces = 각각 Request, Response Content-Type
		apiInfo = Swagger API문서에 대한 설명을 표기하는 메소드
		apis = Swagger API 문서로 만들기 원하는 basePackage 경로
		path = URL 경로를 지정해 해당 URL 요청만 Swagger API문서로 만듬
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.consumes(getConsumeContentTypes())
				.produces(getProduceContentTypes())
				.groupName("Test API")
				.apiInfo(getApiInfo())
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.rest.controller"))
				.paths(PathSelectors.any())
				.build();

	}

	private Set<String> getConsumeContentTypes() {
		Set<String> consumes = new HashSet<>();
		consumes.add("application/json;charset=UTF-8");
		consumes.add("application/x-www-form-urlencoded");
		return consumes;
	}

	private Set<String> getProduceContentTypes() {
		Set<String> produces = new HashSet<>();
		produces.add("application/json;charset=UTF-8");
		return produces;
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("TITLE : ")
				.description("Description : Swagger를 이용한 API문서")
				.termsOfServiceUrl("서버URL?")
				.contact(new Contact("name : 작성자(jdh)", "작성자URL", "작성자 이메일"))
				.version("1.0.1")
				.build();
	}
}
