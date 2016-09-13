package com.mojiayi.learn.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = { "file:config/spring-boot-config.xml" })
public class BootConfig {

}
