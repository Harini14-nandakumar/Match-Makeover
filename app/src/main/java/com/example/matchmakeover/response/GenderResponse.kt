package com.example.matchmakeover.response

import java.util.ArrayList


data class GenderResponse(
    val status: String,
    val message: String,
    val genders: ArrayList<Gender>
)

data class Gender(
    val id: Int,
    val name: String
)

