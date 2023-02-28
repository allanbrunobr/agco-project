package com.br.servicesimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.br.domain.entity")
public class ServicesImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesImplApplication.class, args);
    }

}
