package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.hibernate.annotations.UuidGenerator

data class GameState(
        @Id
        @UuidGenerator
        val id: String = "default",
        @OneToOne(mappedBy = "id")
        val gameId: Game? = null,
        val usernames: List<String>,
        val playerTurn: Int,
        val dice: List<Int>,
        val resources: List<Int>,
        val roads: List<Int>,
        val settlements: List<Int>,
        val cities: List<Int>,
        val biggestRoad: Int
)
