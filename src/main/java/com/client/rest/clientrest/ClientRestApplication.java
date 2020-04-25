package com.client.rest.clientrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class ClientRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientRestApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
