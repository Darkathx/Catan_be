package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime

@Entity
data class UserAccount(

    @Id
    @UuidGenerator
    val id: String = "default",
    @Column(unique = true)
    val username: String = "default",
    var password: String = "default",
    val creationDate: LocalDateTime? = null,
    @Column(unique = true)
    val email: String? = null,
    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "game_history",
        joinColumns = [JoinColumn(name = "user_account_id")],
        inverseJoinColumns = [JoinColumn(name = "match_id")]
    )
    val playedGames: Set<GameRecord>? = null
) {
    constructor(username: String, password: String, creationDate: LocalDateTime, email: String) : this(
     "",
        username = username,
        password = password,
        creationDate = creationDate,
        email = email
    )
}
