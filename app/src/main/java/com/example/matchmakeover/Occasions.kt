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
    private val occasionsList = mutableListOf<Occasions>() // Dynamic list for occasions

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

        // Set up Add button click listener
        addButton.setOnClickListener {
            val intent = Intent(this, NewOccasions::class.java)
            startActivity(intent)
        }

        // Fetch occasions from the API
        fetchOccasions()

        // Set the title
        titleTextView.text = "Occasions"
    }

    private fun fetchOccasions() {
        val call = RetrofitClient.retrofitInstance.create(ApiInterface::class.java).fetchOccasions()

        call.enqueue(object : Callback<OccasionsResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<OccasionsResponse>, response: Response<OccasionsResponse>) {
                if (response.isSuccessful) {
                    val occasionResponse = response.body()
                    if (occasionResponse != null && occasionResponse.status == "success") {
                        occasionsList.clear()
                        occasionsList.addAll(occasionResponse.occasions)
                        occasionAdapter.notifyDataSetChanged()
                    } else {
                        showToast("No occasions data found!")
                    }
                } else {
                    showToast("API error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<OccasionsResponse>, t: Throwable) {
                showToast("Network error: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        fetchOccasions()
    }
}
