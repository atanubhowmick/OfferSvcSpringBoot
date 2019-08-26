package com.atanu.java.spring.offersvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Atanu Bhowmick
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class OfferSvcBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferSvcBootApplication.class, args);
	}

}
