package com.software.pick2flick.authserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.SecurityFilterChain
import java.util.stream.Stream

@Configuration
class SecurityConfig {
    @Bean
    fun httpSecurity(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf {csrf -> csrf.disable()}
            .authorizeHttpRequests { httpRequest -> httpRequest
                .anyRequest().permitAll()
            }
            .build()
    }
}