package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Category
import com.example.matchmakeover.response.ClothingResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPageThree : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserPageThreeAdapter
    private val items = mutableListOf<Category>() // Mutable list to hold clothing items

    private lateinit var floatingButton: FloatingActionButton
    private var selectedCategoryId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page3)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Initialize adapter with click listener
        adapter = UserPageThreeAdapter(items) { selectedCategory: Category ->
            selectedCategoryId = selectedCategory.id.toString() // Store selected category ID
        }
        recyclerView.adapter = adapter

        // Initialize Floating Action Button
        floatingButton = findViewById(R.id.floatingActionButton2)

        // Set up click listener for FAB
        floatingButton.setOnClickListener {
            if (selectedCategoryId.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Please select a category", Toast.LENGTH_SHORT).show()
            } else {
                navigateToPage(Colorgrid::class.java) // Navigate to next page
            }
        }

        // Fetch categories
        fetchCategories()
    }

    private fun fetchCategories() {
        val apiInterface = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
        val call = apiInterface.fetchCategories("kids") // Fetch categories specific to "kids"

        call.enqueue(object : Callback<ClothingResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ClothingResponse>, response: Response<ClothingResponse>) {
                if (response.isSuccessful) {
                    val clothingResponse = response.body()
                    if (clothingResponse != null && clothingResponse.status == "success") {
                        items.clear()
                        items.addAll(clothingResponse.categories)
                        adapter.notifyDataSetChanged()
                    } else {
                        showToast("Failed to load categories")
                    }
                } else {
                    showToast("Error fetching categories: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ClothingResponse>, t: Throwable) {
                Log.e("UserPageThree", "API Error: ${t.message}")
                showToast("Failed to connect to the server. Please try again later.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToPage(targetClass: Class<*>) {
        val intent = Intent(this, targetClass).apply {
            putExtra("SELECTED_CATEGORY_ID", "" + selectedCategoryId)
        }
        startActivity(intent)
    }
}
