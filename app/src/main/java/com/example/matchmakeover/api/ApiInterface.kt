package com.example.matchmakeover.api

import com.example.matchmakeover.response.CategoriesResponse
import com.example.matchmakeover.response.GenderRequest
import com.example.matchmakeover.response.GenderResponse
import com.example.matchmakeover.response.LoginRequest
import com.example.matchmakeover.response.LoginResponse
import com.example.matchmakeover.response.NewCategoriesRequest
import com.example.matchmakeover.response.NewCategoriesResponse
import com.example.matchmakeover.response.NewGenderResponse
import com.example.matchmakeover.response.OccasionsResponse
import com.example.matchmakeover.response.UserRequest
import com.example.matchmakeover.response.UserResponse
import com.example.matchmakeover.responsepackage.ColorResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/signup.php") // Path to the signup API endpoint
    fun signUpUser(@Body userRequest: UserRequest): Call<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/login.php") // Path to the login API endpoint
    fun userLogin(@Body loginRequest: LoginRequest): Call<LoginResponse> // Renamed function to follow standard naming conventions

   @Headers("Content-Type: application/json")
   @GET("matchmakeover/genders.php")  // The endpoint URL for your genders
   fun fetchgender(): Call<GenderResponse>

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/newgenders.php")
    fun addGender(@Body request: GenderRequest): Call<NewGenderResponse>

    @GET("matchmakeover/categories.php") // Endpoint to fetch categories
    fun fetchCategories(): Call<CategoriesResponse>

    @POST("categories/add")
    fun addCategory(@Body categoryRequest: NewCategoriesRequest): Call<NewCategoriesResponse>

    @GET("occasions.php")
    fun fetchOccasions(): Call<OccasionsResponse>

    @GET("colors.php")
    fun fetchColors(): Call<ColorResponse>

}
