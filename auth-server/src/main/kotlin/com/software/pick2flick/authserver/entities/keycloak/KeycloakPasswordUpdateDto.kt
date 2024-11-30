package com.software.pick2flick.authserver.entities.keycloak

data class KeycloakPasswordUpdateDto (
    val credentials: List<KeycloakCredential>
)