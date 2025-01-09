package com.example.matchmakeover.response

data class NewCategoriesRequest(
    val categories_name: String
)
data class NewCategoriesResponse(
    val status: String,
    val message: String
)




