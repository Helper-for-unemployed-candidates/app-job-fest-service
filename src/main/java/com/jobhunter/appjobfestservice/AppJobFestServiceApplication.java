package com.jobhunter.appjobfestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // enabling eureka
public class AppJobFestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppJobFestServiceApplication.class, args);
	}

}
