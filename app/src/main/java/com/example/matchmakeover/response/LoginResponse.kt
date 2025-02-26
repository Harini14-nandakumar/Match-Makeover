package com.example.matchmakeover.response

data class LoginResponse(
    val status: Boolean,
    val message: String,
    val role: Any
)


data class LoginRequest(
    val username: String,
    val password: String
)
