package com.example.datahubwebsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//https://velog.io/@max9106/Spring-Boot-%EC%A0%95%EC%A0%81-%EB%A6%AC%EC%86%8C%EC%8A%A4-%EC%A7%80%EC%9B%90-4mk6f54vqm
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/js/")
//                .setCachePeriod(20);
//
//        registry.addResourceHandler("/").addResourceLocations("/WEB-INF/views/html/index.html")
//                .setCachePeriod(20);

//        registry.addResourceHandler("resources/**") // 핸들러 추가
//                .addResourceLocations("classpath:/static/") // 클래스패스 설정시 끝에 꼭 / 넣어주자.
//                .setCachePeriod(20); // 초단위
    }
//
}
