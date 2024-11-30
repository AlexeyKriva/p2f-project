package com.software.pick2flick.nlpmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NlpMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NlpMicroserviceApplication.class, args);
    }

}
