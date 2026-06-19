package com.send.bulk_mailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BulkMailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulkMailerApplication.class, args);
	}

}
