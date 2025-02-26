package com.example.matchmakeover.response

data class ProductResponse(
    val status: String,
    val products: List<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val genderid: Int,
    val categoriesid: Int,
    val occasionsid: Int,
    val coloursid: Int,
    val images: List<String>
)
