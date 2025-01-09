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
import com.example.matchmakeover.response.Categories
import com.example.matchmakeover.response.CategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Categories : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val categoryList = mutableListOf<Categories>()

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    // Static list for categories if the API fails
    private val defaultCategories: List<Categories>
        get() = listOf(
            Categories("Shirts","T-shirts","Coats","Jackets","Pants","Jeans")
        )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewCategories)
        addButton = findViewById(R.id.ivAddIcon)
        titleTextView = findViewById(R.id.tvTitle)

        // Set up RecyclerView with the category list
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoriesAdapter = CategoriesAdapter(categoryList)
        recyclerView.adapter = categoriesAdapter

        // Set up the Add button click listener
        addButton.setOnClickListener {
            Toast.makeText(this, "Add category clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewCategories::class.java)
            startActivity(intent)
        }

        // Fetch the category list from the API
        fetchCategories()

        // Set the title
        titleTextView.text = "Categories"
    }

    private fun fetchCategories() {
        // Use the API method to fetch categories
        val call = RetrofitClient.instance.create(ApiInterface::class.java).fetchCategories()

        call.enqueue(object : Callback<CategoriesResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                if (response.isSuccessful) {
                    val categoryResponse = response.body()
                    if (categoryResponse != null && categoryResponse.status == "success") {
                        categoryList.clear()
                        categoryList.addAll(categoryResponse.categories)
                        categoriesAdapter.notifyDataSetChanged()
                    } else {
                        showToast("Failed to fetch categories, using defaults.")
//                        useDefaultCategories()
                    }
                } else {
//                    showToast("Error fetching categories, using defaults.")
//                    useDefaultCategories()
                }
            }

            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                showToast("Error: ${t.message}, using defaults.")
                useDefaultCategories()
            }
        })
    }

    private fun useDefaultCategories() {
        categoryList.clear()
        categoryList.addAll(defaultCategories)
        categoriesAdapter.notifyDataSetChanged()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
