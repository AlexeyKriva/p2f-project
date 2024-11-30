package com.software.pick2flick.authserver.controllers

import com.fasterxml.jackson.annotation.JsonProperty
import com.software.pick2flick.authserver.entities.auth.AdminDetails
import com.software.pick2flick.authserver.entities.auth.RefreshTokenDto
import com.software.pick2flick.authserver.entities.auth.UserDetails
import com.software.pick2flick.authserver.entities.keycloak.KeycloakToken
import com.software.pick2flick.authserver.entities.keycloak.UserAuthDetails
import com.software.pick2flick.authserver.entities.keycloak.UserDetailsForPasswordChanging
import com.software.pick2flick.authserver.services.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/auth"], produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class AuthController(private val authService: AuthService) {
    @PostMapping("/users/register")
    fun registrationPassenger(
        @Valid @RequestBody userDetails: UserDetails
    ): ResponseEntity<KeycloakToken> {
        println("I am here")
        return ResponseEntity(authService.saveUser(userDetails), HttpStatus.CREATED)
    }

    @PostMapping("/admins/register")
    fun registrationAdmin(
        @Valid @RequestBody adminDetails: AdminDetails
    ): ResponseEntity<KeycloakToken> {
        return ResponseEntity(authService.saveAdmin(adminDetails), HttpStatus.CREATED)
    }

    @PostMapping("/users")
    fun authUser(
        @Valid @RequestBody userAuthDetails: UserAuthDetails
    ): ResponseEntity<KeycloakToken> {
        return ResponseEntity(authService.passAuthorization(userAuthDetails), HttpStatus.OK)
    }

    @PutMapping("/passwords")
    fun changePassword(
        @Valid @RequestBody userAuthDetails: UserAuthDetails
    ): ResponseEntity<Unit> {
        return ResponseEntity(authService.changePassword(userAuthDetails), HttpStatus.OK)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody @JsonProperty("refreshToken") refreshToken: RefreshTokenDto
    ): ResponseEntity<KeycloakToken> {
        return ResponseEntity(authService.refreshAccessToken(refreshToken.refreshToken), HttpStatus.OK)
    }
}