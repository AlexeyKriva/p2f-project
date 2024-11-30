package com.software.pick2flick.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("imageProcessor", p -> p.path("/api/images/**")
                        .uri("lb://image-processor"))
                .route("movieService", d -> d.path("/api/movies/**")
                        .uri("lb://movie-microservice"))
                .route("nlpService", d -> d.path("/api/keywords/**")
                        .uri("lb://nlp-microservice"))
                .route("authService", r -> r.path("/api/auth/**")
                        .uri("lb://auth-server"))
                .build();
    }
}
