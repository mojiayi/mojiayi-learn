package com.mojiayi.learn.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BootMain extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {
	@Value("${port}")
	private int port;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
