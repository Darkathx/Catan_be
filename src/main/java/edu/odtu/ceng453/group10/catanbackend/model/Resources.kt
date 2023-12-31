package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import org.hibernate.annotations.UuidGenerator

@Entity
data class Resources(
        @Id
        @UuidGenerator
        val id: String = "default",
        @ManyToOne
        val gameStateId: GameState? = null,
        val brick: Int = 0,
        val lumber: Int = 0,
        val wool: Int = 0,
        val grain: Int = 0,
        val ore: Int = 0
)
