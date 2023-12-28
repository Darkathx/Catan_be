package edu.odtu.ceng453.group10.catanbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import org.hibernate.annotations.UuidGenerator

@Entity
data class Game(
       @Id
       @UuidGenerator
       val id: String = "default",

       @OneToOne(mappedBy = "username")
       var p1: UserAccount? = null,
       @OneToOne(mappedBy = "username")
       var p2: UserAccount? = null,
       @OneToOne(mappedBy = "username")
       var p3: UserAccount? = null,
       @OneToOne(mappedBy = "username")
       var p4: UserAccount? = null,
)
