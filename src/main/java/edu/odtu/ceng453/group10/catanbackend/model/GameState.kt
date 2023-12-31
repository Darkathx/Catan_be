package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import org.hibernate.annotations.UuidGenerator

@Entity
data class GameState(
        @Id
        @UuidGenerator
        val id: String = "default",
        @OneToOne(mappedBy = "id")
        val gameId: Game? = null,
        val playerTurn: Int,
        val dice1: Int = 0,
        val dice2: Int = 0,
        @OneToMany(mappedBy = "id")
        val resources: List<Resources>? = null,
        @OneToMany(mappedBy = "id")
        val buildings: List<Buildings>? = null,
)
