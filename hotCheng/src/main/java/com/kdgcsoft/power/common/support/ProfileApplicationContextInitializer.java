package com.kdgcsoft.power.common.support;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ProfileApplicationContextInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileApplicationContextInitializer.class);

	public void initialize(ConfigurableApplicationContext applicationContext) {
		try {
			ClassPathResource resource = new ClassPathResource("profile.properties");
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);

			String profile = properties.getProperty("spring.profiles.active");

			if (profile == null) {
				profile = "development";
			}

			applicationContext.getEnvironment().setActiveProfiles(new String[] { profile });
			logger.info("spring profile {}", profile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}