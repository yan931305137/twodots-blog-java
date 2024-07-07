package com.twodots.blog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.twodots.blog.dao")
@EnableScheduling
public class YebSeverApplication implements WebMvcConfigurer {
    /**
     * 放行Swagger静态文件
     * @param registry
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/doc.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").
//                addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//
//    public static void main(String[] args) {
//        SpringApplication.run(YebSeverApplication.class, args);
//        System.out.println("文档接口:http://localhost:8080/doc.html");
//    }

}