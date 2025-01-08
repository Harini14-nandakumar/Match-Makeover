package com.example.matchmakeover.api

import com.example.matchmakeover.response.UserRequest
import com.example.matchmakeover.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/signup.php") // Path to the signup API endpoint
    fun signUpUser(@Body userRequest: UserRequest): Call<UserResponse>



}
