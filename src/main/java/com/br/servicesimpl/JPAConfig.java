package com.br.servicesimpl;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.br.servicesimpl.repository"})
public class JPAConfig {
}
