package com.example.matchmakeover.response

data class Occasions(
    val id: String,
    val name: String
)

data class OccasionsResponse(
    val status: String,
    val message: String,
    val occasions: List<Occasions>
)
