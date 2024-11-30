package com.software.pick2flick.authserver.entities.keycloak

data class KeycloakCredential(
    val type: String = "password",
    val value: String,
    val temporary: Boolean
)