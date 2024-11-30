package com.software.pick2flick.authserver.entities.keycloak

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class UserDetailsForPasswordChanging (
    @JsonProperty("name")
    @Min(value = 4)
    @NotBlank
    val username: String,

    @JsonProperty("email")
    @Email
    @NotBlank
    val email: String
)