package com.sha.fastercode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("OPTIONS", "PUT", "DELETE", "GET",
                "POST").allowedHeaders("X-requested-with", "Content-Type", "Origin","Content-Range","Range", "authorization");
    }
}
