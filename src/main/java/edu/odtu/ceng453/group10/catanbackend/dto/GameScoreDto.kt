package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data Transfer Object representing a user's game score")
data class GameScoreDto(
    @field:Schema(description = "The username of the player", example = "player123", nullable = true)
    val username: String? = null,

    @field:Schema(description = "The score achieved by the player", example = "42", nullable = true)
    val score: Int? = null
)
