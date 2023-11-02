package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId

@Entity
data class GameScore(
    @EmbeddedId
    val id: ScoreTableKey? = null,

    @ManyToOne
    @MapsId("userAccountId")
    @JoinColumn(name = "user_account_id")
    val userAccount: UserAccount? = null,

    @ManyToOne
    @MapsId("gameRecordId")
    @JoinColumn(name = "match_id")
    val gameRecord: GameRecord? = null,

    val score: Int = 0
)
