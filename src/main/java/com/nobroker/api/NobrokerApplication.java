package com.nobroker.api;

import com.nobroker.api.util.PropertyStatusFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NobrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NobrokerApplication.class, args);
	}
	
}
