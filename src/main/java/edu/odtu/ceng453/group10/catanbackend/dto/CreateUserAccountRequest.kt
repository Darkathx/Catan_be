package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request object for creating a new user account")
data class CreateUserAccountRequest(
    @field:Schema(description = "Username for the new account", example = "john_doe", required = true)
    val username: String,

    @field:Schema(description = "Email address for the new account", example = "john.doe@example.com", required = true)
    val email: String,

    @field:Schema(description = "Password for the new account", example = "securePassword123", required = true)
    val password: String
)
