package com.example.matchmakeover.response

data class UserResponse(
    val status: String,
    val message: String
)

data class UserRequest(
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val role: String
)
