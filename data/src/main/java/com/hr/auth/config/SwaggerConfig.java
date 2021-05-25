package com.hr.auth.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("AUTH")
                .description("API TEST")
                .build();
    }

    @Bean
    public Docket commonApi(){
        List<Parameter> global = new ArrayList<>();
        global.add(new ParameterBuilder().
                       name("Authorization").
                       description("API KEY").
                       parameterType("header").
                       required(true).
                       modelRef(new ModelRef("String")).build());
        

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(global)
                .groupName("auth")
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
        
    }
}
