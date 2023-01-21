package com.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Myblogapp532Application {
	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(Myblogapp532Application.class, args);
	}

}
