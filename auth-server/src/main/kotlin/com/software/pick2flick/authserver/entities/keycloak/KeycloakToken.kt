package com.software.pick2flick.authserver.entities.keycloak

import com.fasterxml.jackson.annotation.JsonProperty

data class KeycloakToken(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("expires_in")
    val expiresIn: Int,

    @JsonProperty("refresh_expires_in")
    val refreshExpiresIn: Int,

    @JsonProperty("refresh_token")
    val refreshToken: String,

    @JsonProperty("token_type")
    val tokenType: String
)