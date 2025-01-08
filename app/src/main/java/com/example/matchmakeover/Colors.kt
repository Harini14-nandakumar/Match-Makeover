package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Colors : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var colorsAdapter: ColorsAdapter
    private val colorsList = listOf("Red", "Blue", "White", "Green", "Pink", "Black")

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colors)

        // Initialize views
        recyclerView = findViewById(R.id.Colors)
        addButton = findViewById(R.id.ivAddIcon) // Ensure this is the correct ID for your add button
        titleTextView = findViewById(R.id.tvTitle)  // Ensure this is the correct ID for the title

        // Set up RecyclerView with the colors
        recyclerView.layoutManager = LinearLayoutManager(this)
        colorsAdapter = ColorsAdapter(colorsList)
        recyclerView.adapter = colorsAdapter

        // Set up the Add button click listener
        addButton.setOnClickListener {
            Toast.makeText(this, "Add Color clicked", Toast.LENGTH_SHORT).show()

            // Navigate to the activity where a new color can be added
            val intent = Intent(this, NewColors::class.java)
            startActivity(intent)
        }

        // Set the title
        titleTextView.text = "Select a Color"
    }
}
