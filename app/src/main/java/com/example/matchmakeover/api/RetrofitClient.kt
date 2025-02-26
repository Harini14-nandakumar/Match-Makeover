package com.example.matchmakeover.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
     private const val BASE_URL = "https://6v1gv1tw-80.inc1.devtunnels.ms/" // Replace with your actual API URL
     const val BASE_URL_IMAGE = "https://6v1gv1tw-80.inc1.devtunnels.ms/matchmakeover/" // Replace with your actual API URL

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(40, TimeUnit.SECONDS) // Read timeout
            .writeTimeout(40, TimeUnit.SECONDS) // Write timeout
            .build()
    }

    val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient) // Attach OkHttpClient
            .addConverterFactory(GsonConverterFactory.create()) // Gson for JSON conversion
            .build()
    }
}
