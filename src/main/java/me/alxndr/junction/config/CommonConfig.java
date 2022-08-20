package me.alxndr.junction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

	@Value("${timer.level-time}")
	private Integer levelTime;

	@Bean
	public Integer levelTime() {
		return levelTime;
	}

}
