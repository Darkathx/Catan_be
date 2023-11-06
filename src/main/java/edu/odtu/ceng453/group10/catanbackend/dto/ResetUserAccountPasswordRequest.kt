package edu.odtu.ceng453.group10.catanbackend.dto

data class ResetUserAccountPasswordRequest (
    val email: String,
    val newPassword: String
)