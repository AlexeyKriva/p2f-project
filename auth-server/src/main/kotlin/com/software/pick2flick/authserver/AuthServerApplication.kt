package com.software.pick2flick.authserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc
@EnableDiscoveryClient
class AuthServerApplication

fun main(args: Array<String>) {
    runApplication<AuthServerApplication>(*args)
}
