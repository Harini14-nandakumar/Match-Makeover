package com.example.matchmakeover.response

data class Categories(
    val id: String,
    val name: String,
    val s: String,
    val s1: String,
    val s2: String,
    val s3: String
)

data class CategoriesResponse(
    val status: String,
    val message: String,
    val categories: List<Categories>
)
