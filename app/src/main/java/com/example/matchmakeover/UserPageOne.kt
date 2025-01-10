package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        itemAdapter = UserPageOneAdapter(items)
        recyclerView.adapter = itemAdapter

        // Female button navigation
        findViewById<Button>(R.id.buttonFemale).setOnClickListener {
            val intent = Intent(this, UserPageTwo::class.java)
            startActivity(intent)
        }

        // Kids button navigation
        findViewById<Button>(R.id.buttonKids).setOnClickListener {
            val intent = Intent(this, UserPageThree::class.java)
            startActivity(intent)
        }

        // FloatingActionButton navigation to UserPageFour
        findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener {
            val intent = Intent(this, Colorgrid::class.java)
            startActivity(intent)
        }
    }

    // Category data class for storing image resource and category name
    data class Category(val imageRes: Int, val name: String)
}
