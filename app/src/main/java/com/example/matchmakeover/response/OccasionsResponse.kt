package com.example.matchmakeover.response

data class Occasions(
    val id: String,
    val name: String,
    val description: String,
    val image: String
)


data class OccasionsResponse(
    val status: String,
    val message: String,
    val occasions: List<Occasions>
)
