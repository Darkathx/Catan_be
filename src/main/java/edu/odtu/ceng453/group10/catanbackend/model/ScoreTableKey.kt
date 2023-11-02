package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class ScoreTableKey(

    @Column(name = "match_id")
    val gameRecordId: String = "",
    @Column(name = "user_account_id")
    val userAccountId: String = ""
): Serializable
