package com.software.pick2flick.authserver.services

import com.software.pick2flick.authserver.entities.auth.AdminDetails
import com.software.pick2flick.authserver.entities.auth.UserDetails
import com.software.pick2flick.authserver.entities.keycloak.KeycloakToken
import com.software.pick2flick.authserver.entities.keycloak.UserAuthDetails
import com.software.pick2flick.authserver.entities.keycloak.UserDetailsForPasswordChanging
import org.springframework.stereotype.Service

@Service
class AuthService(private val keycloakService: KeycloakService) {

    fun saveUser(userDetails: UserDetails): KeycloakToken {
        val keycloakToken = keycloakService.saveUserInKeycloak(userDetails.username,
            userDetails.email, userDetails.password, "ROLE_USER", true)

        return keycloakToken
    }

    fun saveAdmin(adminDetails: AdminDetails): KeycloakToken {
        val keycloakToken = keycloakService.saveUserInKeycloak(adminDetails.username,
            adminDetails.email, adminDetails.password, "ROLE_ADMIN", true)

        return keycloakToken
    }

    fun passAuthorization(userAuthDetails: UserAuthDetails): KeycloakToken {
        return keycloakService.getAccessAndRefreshTokens(userAuthDetails.username, userAuthDetails.password)
    }

    fun changePassword(userAuthDetails: UserAuthDetails): Unit {
        keycloakService.changePassword(userAuthDetails.username,
            userAuthDetails.password)
    }

    fun refreshAccessToken(refreshToken: String): KeycloakToken {
        return keycloakService.refreshToken(refreshToken)
    }
}