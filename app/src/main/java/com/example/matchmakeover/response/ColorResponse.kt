package com.example.matchmakeover.responsepackage

data class ColorResponse(
    val status: String,
    val colors: List<Color>
)

data class Color(
    val name: String  // The name of the color, e.g., "Red", "Blue", etc.
)
