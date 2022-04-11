package com.qry.mbpcen.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//===================================================================
// 호출 주소 : http://127.0.0.1:8091/swagger-ui/index.html	
//-------------------------------------------------------------------
// application.properties 설정
//-------------------------------------------------------------------
//	server.port=8091
//	spring.mvc.pathmatch.matching-strategy=ant-path-matcher
//===================================================================	
	@Bean
	public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("basic")
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.qry.mbpcen.admin"))
                // 스웨거가 RestController를 전부 스캔을 한다.
                // basePackage => 어디를 범위로 스캔을 할 것인지 작성
//                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/basic/.*"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MBPCEN-ADMIN")
                .description("MBPCEN-ADMIN API 서비스 입니다")
                .version("0.0.1")
                .termsOfServiceUrl("https://antstudy.tistory.com/")
                .license("LICENSE")
                .licenseUrl("")
                .build();
    }
}
