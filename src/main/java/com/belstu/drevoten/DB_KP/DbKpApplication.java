package com.belstu.drevoten.DB_KP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class DbKpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbKpApplication.class, args);
	}

}
