package com.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.shoppingcart.repository")
@ComponentScan("com.shoppingcart.serviceImpl")
@ComponentScan("com.shoppingcart.controller")
@EntityScan("com.shoppingcart.model")
@ComponentScan("com.shoppingcart.service")
@ComponentScan("com.shoppingcart.config")
@SpringBootApplication
public class ShoppingCart0Application {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCart0Application.class, args);
	}
}
