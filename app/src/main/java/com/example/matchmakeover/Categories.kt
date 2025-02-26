package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Category
import com.example.matchmakeover.response.ClothingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Categories : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val categoryList = mutableListOf<Category>() // Correct type to ClothingItem

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewCategories)
        addButton = findViewById(R.id.ivAddIcon)
        titleTextView = findViewById(R.id.tvTitle)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoriesAdapter = CategoriesAdapter(categoryList)
        recyclerView.adapter = categoriesAdapter

        // Add button click listener
        addButton.setOnClickListener {
            Toast.makeText(this, "Add category clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewCategories::class.java)
            startActivity(intent)
        }

        // Fetch categories from the API
        fetchCategories()

        // Set the title
        titleTextView.text = "Categories"
    }

    private fun fetchCategories() {
        // Use the API to fetch categories
        val apiInterface = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
        val call = apiInterface.fetchCategories()

        call.enqueue(object : Callback<ClothingResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ClothingResponse>, response: Response<ClothingResponse>) {
                if (response.isSuccessful) {
                    val categoryResponse = response.body()
                    if (categoryResponse != null && categoryResponse.status == "success") {
                        // Populate the category list with fetched categories
                        categoryList.clear()
                        categoryList.addAll(categoryResponse.categories)
                        categoriesAdapter.notifyDataSetChanged()
                    } else {
                        // Notify the user if fetching categories fails
                        showToast("Failed to fetch categories.")
                    }
                } else {
                    // Notify the user if the API returns an error
                    showToast("Error fetching categories.")
                }
            }

            override fun onFailure(call: Call<ClothingResponse>, t: Throwable) {
                // Handle API call failure
                showToast("Error: ${t.message}. Please check your connection and try again.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
