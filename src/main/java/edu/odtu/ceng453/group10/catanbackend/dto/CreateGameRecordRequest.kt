package edu.odtu.ceng453.group10.catanbackend.dto

data class CreateGameRecordRequest(
        val firstPlayerId: String? = null,
        val secondPlayerId: String? = null,
        val thirdPlayerId: String? = null,
        val fourthPlayerId: String? = null,
        val firstPlayerScore: Int = 0,
        val secondPlayerScore: Int = 0,
        val thirdPlayerScore: Int = 0,
        val fourthPlayerScore: Int = 0

)
