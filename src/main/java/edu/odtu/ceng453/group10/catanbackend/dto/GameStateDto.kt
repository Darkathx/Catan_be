package edu.odtu.ceng453.group10.catanbackend.dto

import edu.odtu.ceng453.group10.catanbackend.model.Building
import edu.odtu.ceng453.group10.catanbackend.model.Resources
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.OneToMany

@Schema(description = "Data Transfer Object representing the game state object.")
data class GameStateDto (
        val id: String = "default",
        val gameId: String? = null,
        val playerTurn: Int = 0,
        val dice1: Int = 0,
        val dice2: Int = 0,
        val resources: List<Resources>? = null,
        val buildings: List<Building>? = null,
)