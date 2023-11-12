package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "Data Transfer Object representing the game record creation date.")
data class GameRecordDto(
        @field:Schema(description = "Creation date of Game Record", required = true)
        val createdDate: LocalDateTime? = null
)
