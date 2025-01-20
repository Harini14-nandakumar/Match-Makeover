package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserPageTwo : AppCompatActivity() {

    // Define ClothingItem data class inside the Activity
    data class ClothingItem(val imageResId: Int, val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page2) // Ensure this layout file exists

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCategories)

        // List of items (Clothing items with images and names)
        val items = listOf(
            ClothingItem(R.drawable.boys, "Tops"),
            ClothingItem(R.drawable.western, "Western Wear"),
            ClothingItem(R.drawable.partywear, "Party Wear"),
            ClothingItem(R.drawable.tshirts, "T-shirts"),
            ClothingItem(R.drawable.nightsuits, "Night Suits"),
            ClothingItem(R.drawable.skirts, "Skirts"),
            ClothingItem(R.drawable.jumpsuits, "Jumpsuits"),
            ClothingItem(R.drawable.jeans, "Jeans")
        )

        // Set the layout manager and adapter for RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2) // Display in 2 columns
        recyclerView.adapter = UserPageTwoAdapter(items) // Pass the list of items to the adapter

        // Navigate to another activity using FloatingActionButton
        val floatingButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        floatingButton.setOnClickListener {
            val intent = Intent(this, Colorgrid::class.java) // Replace with the target activity
            startActivity(intent)
        }
    }
}
