package com.jobhunter.appjobfestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient // enabling eureka
@EnableFeignClients
@EnableCaching
public class AppJobFestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppJobFestServiceApplication.class, args);
    }

}
