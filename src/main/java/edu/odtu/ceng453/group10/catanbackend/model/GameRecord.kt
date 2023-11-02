package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime

@Entity
data class GameRecord(

    @Id
    @UuidGenerator
    val id: String = "",
    val dateOfPlay: LocalDateTime? = null,
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL] , mappedBy = "playedGames")
    val usersInGame: Set<UserAccount>? = null
)
