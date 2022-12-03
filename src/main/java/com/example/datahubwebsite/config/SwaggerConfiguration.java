package com.example.datahubwebsite.config;

import jdk.jfr.Enabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * com.example.datahubwebsite
 * @author namjung
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfiguration implements WebMvcConfigurer {
    // HTTPS을 사용시 나중에 추가를 해야되는 코드가 있음.
    @Bean
    public Docket api() {
//        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//        responseMessages.add(new ResponseMessageBuilder().code(200).message("[SUCCESS] 200 OK.").build());
//        responseMessages.add(new ResponseMessageBuilder().code(500).message("[FAIL] 500 SERVER ERROR!").responseModel(new ModelRef("Error")).build());
//        responseMessages.add(new ResponseMessageBuilder().code(404).message("[FAIL] 404 PAGE NOT FOUND!").build());


        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
//                .globalResponseMessage(RequestMethod.POST,responseMessages);
    }

    //swagger 404 문제 해결
    // https://stackoverflow.com/questions/43545540/swagger-ui-no-mapping-found-for-http-request
    // https://pipe0502.tistory.com/entry/swagger2-Whitelabel-Error-Page
    // 접속 루트 : http://localhost:8088/api/swagger-ui.html
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Around Hub Oopen API Test with Swagger")
                .description("설명 부분")
                .version("1.0.0")
                .build();
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    @Bean
    public ResourceBundleMessageSource translator() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("swagger-message");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("utf-8");
        return source;
    }

}
