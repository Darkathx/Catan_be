package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "game")
data class Game(
       @Id
       @UuidGenerator
       val id: String = "default",

       @OneToOne(mappedBy = "game")
       var state: GameState? = null,

       @OneToMany(mappedBy = "curGame")
       var players: List<UserAccount?> = List(4) { null },

       var playerCount: Int = 0
)
