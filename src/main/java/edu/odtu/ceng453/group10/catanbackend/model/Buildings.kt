package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.UuidGenerator

@Entity
data class Buildings(
        @Id
        @UuidGenerator
        val id: String = "default",
        @ManyToOne
        val gameStateId: GameState? = null,
        val user: Int = 0,
        val type: BuildingType? = null,
        val vertexKey1 : String? = null,
        val vertexKey2 : String? = null,

)
