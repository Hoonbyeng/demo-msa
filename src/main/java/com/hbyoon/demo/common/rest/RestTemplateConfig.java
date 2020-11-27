package com.hbyoon.demo.common.rest;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
public class RestTemplateConfig {
	@Autowired
	private RestTemplateProperties properties;

	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(properties.getConnectTimeout()))
                .setReadTimeout(Duration.ofSeconds(properties.getReadTimeout()))
                .build();
    }
	
	@Component
	@ConfigurationProperties(prefix="hmg.rest-template")
	@Setter
	@Getter
	@ToString
	static public class RestTemplateProperties {
		private long connectTimeout = 5; // default 5 seconds
		private long readTimeout = 30;   // default 30 seconds
	}

}
