package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request object for resetting a user account's password")
data class ResetUserAccountPasswordRequest(
    @field:Schema(description = "Email address associated with the user account that needs a password reset",
        example = "user@example.com", required = true)
    val email: String,

    @field:Schema(description = "The new password for the user account",
        example = "newSecurePassword123", required = true)
    val newPassword: String
)
