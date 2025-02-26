package com.example.matchmakeover

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

class UserPageTwo : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserPageTwoAdapter
    private val items = mutableListOf<Category>()

    private var selectedGenderId: String? = null
    private var selectedCategoryId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page2)

        // Retrieve selected gender ID from intent
        selectedGenderId = intent.getStringExtra("SELECTED_GENDER_ID")

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Initialize Adapter with Click Listener
        adapter = UserPageTwoAdapter(items) { selectedCategory: Category ->
            selectedCategoryId = selectedCategory.id.toString() // Store selected category ID
        }
        recyclerView.adapter = adapter

        // Fetch categories based on gender ID
        fetchCategories(selectedGenderId ?: "female")

        // Floating Action Button Navigation
        val floatingButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        floatingButton.setOnClickListener {
            if (selectedCategoryId.isNullOrEmpty()) {
                showToast("Please select a Category")
            } else {
                navigateToColorGrid()
            }
        }
    }

    private fun fetchCategories(gender: String) {
        val apiInterface = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
        val call = apiInterface.fetchCategories(gender)

        call.enqueue(object : Callback<ClothingResponse> {
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
                    showToast("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ClothingResponse>, t: Throwable) {
                Log.e("UserPageTwo", "API Error: ${t.message}")
                showToast("Failed to connect to the server. Please try again later.")
            }
        })
    }

    private fun navigateToColorGrid() {
        val intent = Intent(this, Colorgrid::class.java).apply {
            putExtra("SELECTED_GENDER_ID", selectedGenderId)
            putExtra("SELECTED_CATEGORY_ID", selectedCategoryId)
        }
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
