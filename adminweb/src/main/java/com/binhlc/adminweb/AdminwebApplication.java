package com.binhlc.adminweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"com.binhlc.adminweb"})
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.binhlc.adminweb.repo")
@EntityScan(basePackages = "com.binhlc.adminweb.entity")
@ComponentScan(basePackages = {"com.binhlc.adminweb.controller", "com.binhlc.adminweb.service"})
public class AdminwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminwebApplication.class, args);
	}

}
