package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserPageFour : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page4)

        // Initialize RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCategories)

        // List of items as pairs (imageResId, name)
        val items = listOf(
            Pair(R.drawable.coats, "Formal"),
            Pair(R.drawable.jeans, "Casual"),
            Pair(R.drawable.jackets, "Travel"),
            Pair(R.drawable.tshirts, "College"),
            Pair(R.drawable.western, "Festive"),
            Pair(R.drawable.partywear, "Party Wear")
        )

        // Set up RecyclerView layout manager and adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns
        recyclerView.adapter = UserPageFourAdapter(items) // Pass the list to the adapter

        // Set up FloatingActionButton for navigation
        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)
        floatingActionButton.setOnClickListener {
            // Navigate to UserPageFive
            val intent = Intent(this, UserPageFive::class.java)
            startActivity(intent)
        }
    }
}
