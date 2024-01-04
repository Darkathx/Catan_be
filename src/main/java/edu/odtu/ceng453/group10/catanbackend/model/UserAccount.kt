package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime

/**
 * Entity representing a user's account in the system. This includes the user's identifying information
 * and their associated game records.
 */
@Entity
data class UserAccount(

    /**
     * The primary key of the user account, represented as a UUID.
     */
    @Id
    @UuidGenerator
    val id: String = "default",

    /**
     * The username of the user account, which is unique across all user accounts.
     */
    @Column(unique = true)
    val username: String = "default",

    /**
     * The password for the user account. It is stored in a hashed format for security purposes.
     */
    var password: String = "default",

    /**
     * The date and time when the user account was created. It defaults to null if not provided.
     */
    val creationDate: LocalDateTime? = null,

    /**
     * The email address associated with the user account, which is unique across all user accounts.
     */
    @Column(unique = true)
    val email: String? = null,

    /**
     * The collection of game records associated with the user account, representing the games that the user has played.
     * This relationship is defined with a many-to-many association, with a join table specifying the foreign keys.
     */
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
    val playedGames: Set<GameScore>? = null,

    @ManyToOne
    val curGame: Game? = null

) {
    /**
     * Secondary constructor for the UserAccount class that allows creation of an account with just the username,
     * password, creation date, and email. The ID is intended to be generated automatically.
     */
    constructor(username: String, password: String, creationDate: LocalDateTime, email: String) : this(
        "",
        username = username,
        password = password,
        creationDate = creationDate,
        email = email,
            curGame = null

    )
}
