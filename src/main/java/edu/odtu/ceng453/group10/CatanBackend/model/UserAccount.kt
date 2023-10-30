package edu.odtu.ceng453.group10.CatanBackend.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.UuidGenerator
import java.time.LocalDateTime

@Entity
data class UserAccount(

    @Id
    @UuidGenerator
    val id: String = "default",
    val password: String = "default",
    val creationDate: LocalDateTime? = null,
    val email: String? = null
)