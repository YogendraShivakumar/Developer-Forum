package com.netcracker.developerforum.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created by yogs0616 on 11/27/2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.netcracker.developerforum.rest.controllers"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "DEVELOPER FORUM REST API",
                "For more info visit https://github.com/YogendraShivakumar/developer-forum",
                "1.0",
                "",
                new Contact("Yogendra J S", "", "yogendra.s.shetty@gmail.com"),
                "",
                "",
                new ArrayList<>()
        );
    }
}

