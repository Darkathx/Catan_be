package edu.odtu.ceng453.group10.catanbackend.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request object for creating a new game record and game score")
data class CreateGameRecordRequest(
        @field:Schema(description = "Id of Player1", example = "player1_id", required = true)
        val firstPlayerId: String? = null,
        @field:Schema(description = "Id of Player2", example = "player2_id", required = true)
        val secondPlayerId: String? = null,
        @field:Schema(description = "Id of Player3", example = "player3_id", required = true)
        val thirdPlayerId: String? = null,
        @field:Schema(description = "Id of Player4", example = "player4_id", required = true)
        val fourthPlayerId: String? = null,
        @field:Schema(description = "Score of Player1", example = "6", required = true)
        val firstPlayerScore: Int = 0,
        @field:Schema(description = "Score of Player2", example = "6", required = true)
        val secondPlayerScore: Int = 0,
        @field:Schema(description = "Score of Player3", example = "6", required = true)
        val thirdPlayerScore: Int = 0,
        @field:Schema(description = "Score of Player4", example = "6", required = true)
        val fourthPlayerScore: Int = 0

)
