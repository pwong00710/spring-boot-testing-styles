package io.tpd.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.tpd.superheroes"})
public class MvcTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcTestsApplication.class, args);
	}
}
