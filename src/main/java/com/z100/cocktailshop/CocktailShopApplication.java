package com.z100.cocktailshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableConfigurationProperties
@EnableJpaRepositories("com.z100.cocktailshop")
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class CocktailShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailShopApplication.class, args);
	}
}
