package com.multipedidos.componente_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.multipedidos.componente_a.model")
@EnableJpaRepositories(basePackages = "com.multipedidos.componente_a.repository")  
public class ComponenteAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComponenteAApplication.class, args);
    }
}
