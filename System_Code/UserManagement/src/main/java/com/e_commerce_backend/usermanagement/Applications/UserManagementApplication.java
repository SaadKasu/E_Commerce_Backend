package com.e_commerce_backend.usermanagement.Applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.e_commerce_backend.usermanagement.Models")
@ComponentScan(basePackages = "com.e_commerce_backend.usermanagement")
@EnableJpaRepositories(basePackages = "com.e_commerce_backend.usermanagement.Repositories")
public class UserManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

}
