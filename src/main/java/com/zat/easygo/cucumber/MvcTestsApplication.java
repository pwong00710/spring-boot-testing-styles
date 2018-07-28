package com.zat.easygo.cucumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zat.easygo.cucumber"})
public class MvcTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcTestsApplication.class, args);
	}
}
