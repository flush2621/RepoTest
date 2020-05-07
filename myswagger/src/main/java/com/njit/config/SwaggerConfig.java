package com.njit.config;

import com.njit.controller.HelloController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docketA(Environment environment){
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("A")
                .enable(flag);
    }

    @Bean
    public Docket docketB(Environment environment){
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("B")
                .enable(flag) //是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.njit.controller"))
                .paths(PathSelectors.ant("/test/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("milo","https://baidu.com","6651815@qq.com");
        return new ApiInfo(
                "Milo's Swagger Documents",
                "Hello Swagger",
                "v1.0",
                "https://baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
