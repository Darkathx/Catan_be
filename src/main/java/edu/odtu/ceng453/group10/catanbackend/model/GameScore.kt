package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

/**
 * Represents a score entry for a game. This entity contains the score details for a specific game and player.
 */
@Entity
data class GameScore(
    /**
     * The composite primary key for the GameScore entity, represented as an embedded ID.
     */
    @EmbeddedId
    val id: ScoreTableKey? = null,

    /**
     * The user account associated with this score. The user account's ID is part of the composite key.
     * It is mapped by the 'userAccountId' field of the composite key.
     */
    @ManyToOne
    @MapsId("userAccountId")
    @JoinColumn(name = "user_account_id")
    val userAccount: UserAccount? = null,

    /**
     * The game record associated with this score. The game record's ID is part of the composite key.
     * It is mapped by the 'gameRecordId' field of the composite key.
     */
    @ManyToOne
    @MapsId("gameRecordId")
    @JoinColumn(name = "match_id")
    val gameRecord: GameRecord? = null,

    val score: Int = 0
)
