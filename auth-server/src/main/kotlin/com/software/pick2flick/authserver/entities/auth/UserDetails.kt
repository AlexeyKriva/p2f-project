package com.software.pick2flick.authserver.entities.auth

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserDetails(
    @JsonProperty("name")
    @NotBlank(message = "Name cannot be blank.")
    @Size(
        min = 4, max = 55, message = "The number of characters in the name" +
                " must be at least 4 and not exceed 55."
    )
    val username: String,

    @JsonProperty("email")
    @Email(message = "Invalid email format.")
    @NotBlank(message = "Email cannot be blank.")
    val email: String,

    @JsonProperty("password")
    @Size(
        min = 8, max = 55, message = "The number of characters in the password" +
                " must be at least 8 and not exceed 55."
    )
    @NotBlank(message = "Password cannot be blank")
    val password: String
)