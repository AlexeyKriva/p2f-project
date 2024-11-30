package com.software.pick2flick.authserver.entities.keycloak

data class KeycloakUser(
    val username: String,
    val enabled: Boolean,
    val firstName: String,
    val lastName: String,
    val email: String,
    val emailVerified: Boolean,
    val credentials: List<KeycloakCredential>
)