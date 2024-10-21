package com.perinfinity.applicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
public class ApplicationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationserviceApplication.class, args);
	}

}
