package com.yash.Scope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScopeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScopeApplication.class, args);
	}

}
