package com.studydesk.desk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"com.studydesk.desk.persistence"})
public class DeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeskApplication.class, args);
	}

}
