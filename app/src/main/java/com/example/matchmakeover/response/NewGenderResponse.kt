package com.example.matchmakeover.response

// This is the correct version of NewGenderResponse, which includes the request data as part of the response.
data class NewGenderResponse(
    val status: String,
    val message: String,
    val gender_data: GenderRequest // Include the request data in the response
)

// Data class for the request, which will be sent to the server
data class GenderRequest(
    val gender_name: String
)

