package com.pers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ParcelCalc {

	public static void main(String[] args) {
		SpringApplication.run(ParcelCalc.class, args);
	}
}
