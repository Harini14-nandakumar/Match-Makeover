package com.example.matchmakeover

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UserPageFive(blue: Int, s: String) : AppCompatActivity() {

    val description: CharSequence? = null
    val imageRes: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page5) // Ensure this matches your layout file

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Sample outfits data
        val outfits = listOf(
            UserPageFive(R.drawable.blue, "Casual Look"),
            UserPageFive(R.drawable.casualt, "Royal Blue"),
            UserPageFive(R.drawable.pants, "Black & White")
        )

        // Set the adapter to the RecyclerView
        recyclerView.adapter = UserPageFiveAdapter(outfits)
    }
}
