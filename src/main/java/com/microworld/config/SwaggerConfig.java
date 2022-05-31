package com.microworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;


/**
 * @author birdbro
 * @date 9:12 2022-4-28
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static String BASE_PACKAGE = "com.microworld.vault.modules";

    @Bean
    @Order(value = 1)
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Date.class, java.util.Date.class);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("I-VAULT")
                .description("<div style='font-size:14px;color:red;'>I-VAULT</div>")
                .termsOfServiceUrl("https://code.rdc.aliyun.com/")
                .contact(new Contact("birdbro","", ""))
                .version("1.0.0")
                .build();
    }






}
