package com.atanu.java.spring.offersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Atanu Bhowmick
 *
 */

@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OfferSvcBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferSvcBootApplication.class, args);
	}

}
