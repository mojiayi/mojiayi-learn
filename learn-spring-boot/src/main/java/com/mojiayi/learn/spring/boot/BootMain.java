package com.mojiayi.learn.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BootMain extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
	private static final Logger LOG = LoggerFactory.getLogger("ROLLING_FILE");
	//private static final Logger LOG_BIG = LoggerFactory.getLogger("ROLLING_BIG_FILE");
	
	@Value("${port}")
	private int port;

	public static void main(String[] args) {
		LOG.info("output log in level info");
		//LOG_BIG.debug("output log in level debug");
		Object[] sources = {BootMain.class, "file:resources/spring-boot-config.xml"};
		SpringApplication.run(sources, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootMain.class);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(port);
	}
}
