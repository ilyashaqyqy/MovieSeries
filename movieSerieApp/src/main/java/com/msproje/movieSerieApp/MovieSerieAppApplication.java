package com.msproje.movieSerieApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "model")
public class MovieSerieAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieSerieAppApplication.class, args);
	}

}
