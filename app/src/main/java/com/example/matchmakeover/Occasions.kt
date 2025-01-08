package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class Occasions : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var occasionAdapter: OccasionAdapter
    private val occasionsList = listOf("Formal", "Casual", "Travel", "Festival", "Party wear", "College")

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occasions)

        recyclerView = findViewById(R.id.recyclerViewCategories)
        addButton = findViewById(R.id.ivAddIcon)
        titleTextView = findViewById(R.id.tvTitle)

        recyclerView.layoutManager = LinearLayoutManager(this)
        occasionAdapter = OccasionAdapter(occasionsList)
        recyclerView.adapter = occasionAdapter

        addButton.setOnClickListener {

            Toast.makeText(this, "Add Categories clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, NewOccasions::class.java)
            startActivity(intent)
        }

        titleTextView.text = "Select an Occasions"
    }
}
