package com.software.pick2flick.moviemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableElasticsearchRepositories
@EnableDiscoveryClient
public class MovieMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieMicroserviceApplication.class, args);
    }

}
