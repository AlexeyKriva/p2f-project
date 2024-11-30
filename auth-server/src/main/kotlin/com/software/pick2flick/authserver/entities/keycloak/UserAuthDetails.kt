package com.software.pick2flick.authserver.entities.keycloak

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class  UserAuthDetails (
    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank.")
    val username: String,

    @JsonProperty("password")
    @NotBlank(message = "Password cannot be blank")
    @Min(value = 8, message = "Password cannot has less than 8 symbols.")
    val password: String
)