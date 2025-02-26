package com.example.matchmakeover.response

data class ClothingResponse(
    val status: String,
    val message: String,
    val categories: List<Category>
)

data class Category(
    val id: Int,
    val name: String,
    val image: String
)
