package com.example.matchmakeover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserPageThree : AppCompatActivity() {

    // Define the data class for the items
    data class ClothingItem(val imageResId: Int, val name: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page3)  // Make sure the layout file exists

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCategories)  // RecyclerView ID

        // List of items to be displayed in the RecyclerView
        val items = listOf(
            ClothingItem(R.drawable.boys, "Boys' Sets"),
            ClothingItem(R.drawable.girls, "Girls' Sets"),
            ClothingItem(R.drawable.kidst, "Kids' T-shirts"),
            ClothingItem(R.drawable.jeans, "Jeans"),
            ClothingItem(R.drawable.kjackets, "Jackets"),
            ClothingItem(R.drawable.kidsnw, "Nightwear"),
            ClothingItem(R.drawable.sweaters, "Sweaters"),
            ClothingItem(R.drawable.summer, "Summer Wear")
        )

        // Set the layout manager and the adapter for the RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)  // Display in 2 columns
        recyclerView.adapter = UserPageThreeAdapter(items)  // Set the adapter
    }
}
