package com.example.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.groupName("test")
				.pathMapping("")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.user.controller"))	//通过指定扫描包暴露接口
				.paths(PathSelectors.any())	//设置过滤规则暴露接口
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Api")
				.description("接口测试")
				.version("1.0")
				.termsOfServiceUrl("")
				.license("")
				.licenseUrl("")
				.build();
	}
}
