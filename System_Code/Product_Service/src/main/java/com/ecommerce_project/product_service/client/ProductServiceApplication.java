package com.ecommerce_project.product_service.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.ecommerce_project.product_service.controllers.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecommerce_project.product_service")
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
