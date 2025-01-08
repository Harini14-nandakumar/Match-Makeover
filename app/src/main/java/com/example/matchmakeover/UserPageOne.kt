package com.example.matchmakeover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserPageOne : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: UserPageOneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page1)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Create a list of categories to display in the RecyclerView
        val items = listOf(
            Category(R.drawable.shirts, "Shirts"),
            Category(R.drawable.tshirts, "T-Shirts"),
            Category(R.drawable.jackets, "Jackets"),
            Category(R.drawable.coats, "Coats"),
            Category(R.drawable.jeans, "Jeans"),
            Category(R.drawable.pants, "Pants")
        )

        // Initialize and set the adapter
        itemAdapter = UserPageOneAdapter(items) // Pass the list of categories (items)
        recyclerView.adapter = itemAdapter
    }

    // Category data class for storing image resource and category name
    data class Category(val imageRes: Int, val name: String)
}
