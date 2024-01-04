package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "game_state")
data class GameState(
        @Id
        @UuidGenerator
        val id: String = "default",

        @OneToOne
        @JoinColumn(name = "game_id", referencedColumnName = "id")
        var game: Game? = null,

        val playerTurn: Int = 0,
        val dice1: Int = 0,
        val dice2: Int = 0,

        @OneToMany(mappedBy = "gameStateId")
        val resources: List<Resources>? = null,

        @OneToMany(mappedBy = "gameStateId")
        val buildings: List<Building>? = null,
)
