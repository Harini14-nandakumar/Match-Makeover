package com.example.matchmakeover.response
data class ChatRequest(
    val username: String,
    val message: String
)

data class ChatResponse(
    val username: String,
    val userMessage: String,
    val botResponse: String,
    val status: String
)
