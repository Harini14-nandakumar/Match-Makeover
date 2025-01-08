package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Categories : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoriesAdapter
    private val categoriesList = listOf("Shirts", "T-shirts", "Jackets", "Coats", "Pants", "Trousers")

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewCategories)
        addButton = findViewById(R.id.ivAddIcon) // Ensure this is the correct ID for your add button
        titleTextView = findViewById(R.id.tvTitle)  // Ensure this is the correct ID for the title

        // Set up RecyclerView with the categories
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoriesAdapter(categoriesList)
        recyclerView.adapter = categoryAdapter

        // Set up the Add button click listener
        addButton.setOnClickListener {
            Toast.makeText(this, "Add Category clicked", Toast.LENGTH_SHORT).show()

            // Navigate to the activity where a new category can be added
            val intent = Intent(this, NewCategories::class.java)
            startActivity(intent)
        }

        // Set the title
        titleTextView.text = "Select a Category"
    }
}
