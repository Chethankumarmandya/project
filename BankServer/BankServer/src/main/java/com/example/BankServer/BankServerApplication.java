package com.example.BankServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BankServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServerApplication.class, args);
	}

}
