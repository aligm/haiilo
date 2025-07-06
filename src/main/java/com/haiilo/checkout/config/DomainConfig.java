package com.haiilo.checkout.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.haiilo.checkout")
@EnableJpaRepositories("com.haiilo.checkout")
@EnableTransactionManagement
public class DomainConfig {
}
