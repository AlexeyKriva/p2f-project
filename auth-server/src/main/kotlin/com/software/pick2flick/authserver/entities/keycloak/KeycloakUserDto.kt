package com.software.pick2flick.authserver.entities.keycloak

import com.fasterxml.jackson.annotation.JsonProperty

data class KeycloakUserDto (
    @JsonProperty("id")
    val id: String,

    @JsonProperty("username")
    val username: String,

    @JsonProperty("firstName")
    val firstName: String,

    @JsonProperty("lastName")
    val lastName: String,

    @JsonProperty("email")
    val email: String
)