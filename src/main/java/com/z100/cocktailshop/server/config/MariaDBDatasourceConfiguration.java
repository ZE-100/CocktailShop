package com.z100.cocktailshop.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.javachinna.repo.prime",
		entityManagerFactoryRef = "mariadbEntityManager",
		transactionManagerRef = "mariadbTransactionManager")
public class MariaDBDatasourceConfiguration {


}
