package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

/**
 * Embeddable composite key class that represents the primary key of the GameScore entity.
 * This key is used to uniquely identify a game score based on the game and the user account.
 */
@Embeddable
data class ScoreTableKey(
    /**
     * The identifier of the game record. It corresponds to the primary key of the game.
     */
    @Column(name = "match_id")
    val gameRecordId: String = "",

    /**
     * The identifier of the user account. It corresponds to the primary key of the user.
     */
    @Column(name = "user_account_id")
    val userAccountId: String = ""
): Serializable // Implementing Serializable as it's a requirement for composite keys in JPA.
