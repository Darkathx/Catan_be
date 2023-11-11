package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request object for user account login")
data class LoginUserAccountRequest(
    @field:Schema(description = "Email address associated with the user account",
        example = "user@example.com", required = true)
    val email: String,

    @field:Schema(description = "Password for the user account",
        example = "password123", required = true)
    val password: String
)
