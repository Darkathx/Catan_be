package edu.odtu.ceng453.group10.catanbackend.dto

data class CreateUserAccountRequest(
    val username: String,
    val email: String,
    val password: String
)
