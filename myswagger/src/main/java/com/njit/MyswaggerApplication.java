package com.njit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MyswaggerApplication extends SpringBootServletInitializer {
//继承SpringBootServletInitializer
    @Override
    //重写configure方法
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(MyswaggerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyswaggerApplication.class, args);
    }

}
