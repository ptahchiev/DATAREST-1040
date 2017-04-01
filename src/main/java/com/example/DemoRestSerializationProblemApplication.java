package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class DemoRestSerializationProblemApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoRestSerializationProblemApplication.class, args);
    }

    @Bean(name = { "defaultDemoRepositoryRestConfigurer" })
    public RepositoryRestConfigurer defaultNemesisRepositoryRestConfigurer() {
        return new DemoRestConfigurerAdapter();
    }
}
