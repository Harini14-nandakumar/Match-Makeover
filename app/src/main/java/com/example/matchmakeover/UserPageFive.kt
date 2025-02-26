package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Occasions
import com.example.matchmakeover.response.OccasionsResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPageFive : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var occasionAdapter: UserPageFiveAdapter
    private val occasions = mutableListOf<Occasions>()

    private var selectedOccasionId: String? = null
    private var selectedOccasionImageUrl: String? = null
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page5)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        fab = findViewById(R.id.floatingActionButton2) // Initialize FAB
        fab.setOnClickListener {
            if (selectedOccasionId.isNullOrEmpty()) {
                showToast("Please select an occasion first")
            } else {
                navigateToNextPage()
            }
        }

        // Get selected occasion details from intent
        selectedOccasionId = intent.getStringExtra("SELECTED_OCCASION_ID")
        selectedOccasionImageUrl = intent.getStringExtra("SELECTED_OCCASION_IMAGE_URL")

        fetchOccasionDetails()
    }

    private fun fetchOccasionDetails() {
        val apiService = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
        val call = apiService.fetchOccasions()

        call.enqueue(object : Callback<OccasionsResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<OccasionsResponse>, response: Response<OccasionsResponse>) {
                if (response.isSuccessful) {
                    val occasionsResponse = response.body()
                    if (occasionsResponse != null && occasionsResponse.status == "success") {
                        occasions.clear()
                        occasions.addAll(occasionsResponse.occasions.filter { it.id == selectedOccasionId })

                        occasionAdapter = UserPageFiveAdapter(occasions)
                        recyclerView.adapter = occasionAdapter
                    } else {
                        showToast("No occasion details found")
                    }
                } else {
                    showToast("Error fetching occasion details: ${response.message()}")
                    Log.e("UserPageFive", "API error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<OccasionsResponse>, t: Throwable) {
                showToast("Failed to connect to server. Please try again later.")
                Log.e("UserPageFive", "Network error: ${t.localizedMessage}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToNextPage() {
        val intent = Intent(this, Chatbox::class.java).apply {
            putExtra("SELECTED_OCCASION_ID", selectedOccasionId)
            putExtra("SELECTED_OCCASION_IMAGE_URL", selectedOccasionImageUrl)
        }
        startActivity(intent)
    }
}
