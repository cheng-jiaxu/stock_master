package com.ming.stock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact= new Contact().name("cheng-jiaxu")
                .url("https://github.com/cheng-jiaxu")
                .email("3245656116@qq.com");

        return new OpenAPI().info(new Info()
                    .title("今日指数-在线接口文档")
                .description("方便前后端快速了解开发接口需求的在线接口API文档")
                    .contact(contact)
                    .version("1.0.0")
                    .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT"))
        );
    }
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan("com.ming.stock.controller")
                .pathsToMatch("/**")
                .build();
    }


}
