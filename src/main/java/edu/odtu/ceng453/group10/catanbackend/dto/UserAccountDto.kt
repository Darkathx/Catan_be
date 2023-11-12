package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data Transfer Object representing the user account details")
data class UserAccountDto(
    @field:Schema(description = "The username of the user account", example = "user123", required = true)
    val username: String = "",

    @field:Schema(description = "The email address of the user account", example = "user@example.com", nullable = true)
    val email: String? = null
)
