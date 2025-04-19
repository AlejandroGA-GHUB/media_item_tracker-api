package com.media_item_tracker.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.media_item_tracker.demo.repository")
public class MediaItemTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaItemTrackerApplication.class, args);
	}

}
