package com.dzo.test_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.dzo.test_bank.persistence.model")
@EnableJpaRepositories(basePackages = {"com.dzo.test_bank.persistence.repository"})


public class TestBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBankApplication.class, args);
	}

}
