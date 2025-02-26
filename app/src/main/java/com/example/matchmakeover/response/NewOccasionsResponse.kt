package com.example.matchmakeover.response

data class NewOccasionsResponse(
    val status: String,
    val occasions: List<Occasions>
) {
    val message: Any
        get() {
            TODO()
        }
}

data class NewOccasionsRequest(
    val id: String // or Int depending on your API response
)
