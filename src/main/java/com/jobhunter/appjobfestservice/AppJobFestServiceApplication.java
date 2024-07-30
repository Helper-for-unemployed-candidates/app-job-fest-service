package com.jobhunter.appjobfestservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient // enabling eureka
@EnableFeignClients
@EnableCaching
public class AppJobFestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppJobFestServiceApplication.class, args);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(
                        new SecurityRequirement().addList("Bearer Authentication")
                )
                .components(
                        new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
                )
                .info(
                        new Info()
                                .title("APP-JOB-FEST service API")
                                .description("This api was generated using springdoc for user-service app")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Safixon Abdusattorov")
                                                .email("safixongg@gmail.com")
                                ).license(
                                        new License()
                                                .name("Licence of API")
                                                .url("API licence url")
                                )
                );
    }


    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
