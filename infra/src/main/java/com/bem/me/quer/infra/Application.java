package com.bem.me.quer.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.bem.me.quer")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
