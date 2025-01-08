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

class Gender : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var genderAdapter: GenderAdapter
    private val genderList = listOf("Male", "Female")

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        // Initialize views
        recyclerView = findViewById(R.id.Genders) // Ensure this ID matches your XML layout
        addButton = findViewById(R.id.ivAddIcon)  // Ensure this is the correct ID for your add button
        titleTextView = findViewById(R.id.tvTitle)  // Ensure this is the correct ID for your title

        // Set up RecyclerView with the gender list
        recyclerView.layoutManager = LinearLayoutManager(this)
        genderAdapter = GenderAdapter(genderList)
        recyclerView.adapter = genderAdapter

        // Set up the Add button click listener
        addButton.setOnClickListener {
            Toast.makeText(this, "Add Gender clicked", Toast.LENGTH_SHORT).show()

            // Navigate to the activity where a new gender can be added (for example, NewGenderActivity)
            val intent = Intent(this, NewGender::class.java)
            startActivity(intent)
        }

        // Set the title
        titleTextView.text = "Select Gender"
    }
}
