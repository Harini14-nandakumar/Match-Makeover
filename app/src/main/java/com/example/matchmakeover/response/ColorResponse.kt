package com.example.matchmakeover.response

data class ColorResponse(
    val status: String,
    val colors: List<Color>
)

data class Color(
    val name: String
)
