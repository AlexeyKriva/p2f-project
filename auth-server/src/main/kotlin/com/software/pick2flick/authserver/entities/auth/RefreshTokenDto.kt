package com.software.pick2flick.authserver.entities.auth

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class RefreshTokenDto (
    @NotBlank(message = "Refresh token cannot be blank.")
    @JsonProperty("refreshToken")
    val refreshToken: String
)