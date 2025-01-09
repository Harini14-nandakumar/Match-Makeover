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
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Occasions
import com.example.matchmakeover.response.OccasionsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Occasions : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var occasionAdapter: OccasionAdapter
    private val occasionsList = mutableListOf<Occasions>() // Empty mutable list for dynamic data

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_occasions)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewCategories)
        addButton = findViewById(R.id.ivAddIcon)
        titleTextView = findViewById(R.id.tvTitle)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        occasionAdapter = OccasionAdapter(occasionsList)
        recyclerView.adapter = occasionAdapter

        // Fetch occasions from the API
        fetchOccasions()

        // Handle Add button click
        addButton.setOnClickListener {
            Toast.makeText(this, "Add Occasions clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewOccasions::class.java)
            startActivity(intent)
        }

        // Set the title
        titleTextView.text = "Select an Occasion"
    }

    private fun fetchOccasions() {
        // Use Retrofit to call the API
        val api = RetrofitClient.instance.create(ApiInterface::class.java)
        val call = api.fetchOccasions()

        call.enqueue(object : Callback<OccasionsResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<OccasionsResponse>, response: Response<OccasionsResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == "success") {
                        occasionsList.clear()
                        occasionsList.addAll(body.occasions)
                        occasionAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@Occasions, "Failed: ${body?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Occasions, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OccasionsResponse>, t: Throwable) {
                Toast.makeText(this@Occasions, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
