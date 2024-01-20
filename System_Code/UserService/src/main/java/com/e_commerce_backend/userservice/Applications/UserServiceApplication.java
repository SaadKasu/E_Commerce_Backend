package com.e_commerce_backend.userservice.Applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.e_commerce_backend.usermanagement.Models")
@ComponentScan(basePackages = "com.e_commerce_backend.userservice")
@EnableJpaRepositories(basePackages = "com.e_commerce_backend.userservice.Repositories")
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
