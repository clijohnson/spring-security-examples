package com.hashedin.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.hashedin.domain",
		"com.hashedin.security"
})
@EnableJpaRepositories(basePackages =  {
		"com.hashedin.domain.repository",
		"com.hashedin.security.repository"
})
@EntityScan(basePackages =  {
		"com.hashedin.domain.entity",
		"com.hashedin.security.entity"
})
public class SpringSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityExampleApplication.class, args);
	}

}
