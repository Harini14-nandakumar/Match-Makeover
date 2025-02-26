package com.example.matchmakeover.api

import com.example.matchmakeover.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/signup.php")
    fun signUpUser(@Body userRequest: UserRequest): Call<UserResponse>

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/login.php")
    fun userLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Headers("Content-Type: application/json")
    @GET("matchmakeover/genders.php")
    fun fetchGenders(): Call<GenderResponse>

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/newgenders.php")
    fun addGender(@Body request: GenderRequest): Call<NewGenderResponse>

    @Headers("Content-Type: application/json")
    @GET("matchmakeover/categories.php")
    fun fetchCategories(@Query("gender") gender: String = "all"): Call<ClothingResponse>

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/categories/add.php")
    fun addCategory(@Body categoryRequest: NewCategoriesRequest): Call<NewCategoriesResponse>

    @Headers("Content-Type: application/json")
    @GET("matchmakeover/occasions.php")
    fun fetchOccasions(): Call<OccasionsResponse>

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/occasions/add.php") // Added missing annotation
    fun addOccasion(@Body occasionRequest: NewOccasionsRequest): Call<NewOccasionsResponse> // Fixed return type

    @Headers("Content-Type: application/json")
    @GET("matchmakeover/colors.php")
    fun fetchColors(): Call<ColorResponse>

    @Headers("Content-Type: application/json")
    @GET("matchmakeover/fetchproduct.php")
    fun getProducts(
        @Query("genderid") genderId: Int? = null,
        @Query("categoriesid") categoryId: Int? = null,
        @Query("occasionsid") occasionId: Int? = null,
        @Query("coloursid") colourId: Int? = null
    ): Call<ProductResponse> // Fetch filtered products

    @Headers("Content-Type: application/json")
    @POST("matchmakeover/geminiai.php")
    fun sendMessage(@Body request: ChatRequest): Call<ChatResponse> // Send message to AI chat system
}
