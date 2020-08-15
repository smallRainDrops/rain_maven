package com.example.crudproject.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置类
 *
 * @author za-shaojian
 */
@Configuration
@EnableSwagger2
@Slf4j
public class Swagger2 {
    private static final Logger log = LoggerFactory.getLogger(Swagger2.class);

    @Bean
    public Docket createRestApi() {
        log.warn("swagger已经初始化完成");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.crudproject.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("众安 biz 服务构建RESTful APIs")
                .description("众安 biz 服务API文档")
                .termsOfServiceUrl("http://www.zhongan.com/")
                .contact("za-shaojian")
                .version("1.0")
                .build();
    }

}
