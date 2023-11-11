package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime

/**
 * Represents a record of a game played, including the participants and the date of the game.
 * This entity is linked to the UserAccount entity in a many-to-many relationship.
 */
@Entity
data class GameRecord(
    /**
     * The unique identifier for the game record. This ID is generated automatically.
     */
    @Id
    @UuidGenerator
    val id: String = "",

    /**
     * The date and time when the game was played.
     */
    val dateOfPlay: LocalDateTime? = null,

    /**
     * A set of UserAccount entities that participated in the game.
     * This is eagerly fetched and changes to the game record will cascade to the user accounts.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL] , mappedBy = "playedGames")
    val usersInGame: Set<UserAccount>? = null
)
