package com.example.matchmakeover.response

data class LoginResponse(
    val status: Boolean,
    val message: String
)

data class LoginRequest(
    val username: String,
    val password: String
)
