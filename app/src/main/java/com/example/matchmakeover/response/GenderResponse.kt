package com.example.matchmakeover.response

data class Gender(
    val id: String,
    val name: String
)
data class GenderResponse(
    val status: String,
    val message: String,
    val genders: List<Gender>
)
