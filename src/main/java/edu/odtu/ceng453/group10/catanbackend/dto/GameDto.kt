package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Data Transfer Object representing the game object.")
data class GameDto (
        val id: String = "default",
        val stateId: String? = null,
        val players: List<String?> = List(2) { null },
        val playerCount: Int = 0
)