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

class UserPageFour : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var occasionAdapter: UserPageFourAdapter
    private val occasions = mutableListOf<Occasions>()

    private lateinit var floatingButton: FloatingActionButton
    private var selectedOccasionId: String? = null
    private var selectedOccasionImageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page4)

        recyclerView = findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        occasionAdapter = UserPageFourAdapter(occasions) { selectedOccasion ->
            selectedOccasionId = selectedOccasion.id
            selectedOccasionImageUrl = selectedOccasion.image // Store selected image URL
            Log.d("UserPageFour", "Selected Occasion ID: $selectedOccasionId")
        }
        recyclerView.adapter = occasionAdapter

        floatingButton = findViewById(R.id.floatingActionButton2)
        floatingButton.setOnClickListener {
            if (selectedOccasionId.isNullOrEmpty()) {
                showToast("Please select an Occasion")
            } else {
                navigateToNextPage()
            }
        }

        fetchOccasions()
    }

    private fun fetchOccasions() {
        val apiService = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)
        val call = apiService.fetchOccasions()

        call.enqueue(object : Callback<OccasionsResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<OccasionsResponse>, response: Response<OccasionsResponse>) {
                if (response.isSuccessful) {
                    val occasionsResponse = response.body()
                    if (occasionsResponse != null && occasionsResponse.status == "success") {
                        occasions.clear()
                        occasions.addAll(occasionsResponse.occasions)
                        occasionAdapter.notifyDataSetChanged()
                        Log.d("UserPageFour", "Occasions loaded successfully")
                    } else {
                        showToast("No occasions found")
                    }
                } else {
                    showToast("Error fetching occasions: ${response.message()}")
                    Log.e("UserPageFour", "API error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<OccasionsResponse>, t: Throwable) {
                showToast("Failed to connect to server. Please try again later.")
                Log.e("UserPageFour", "Network error: ${t.localizedMessage}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToNextPage() {
        val intent = Intent(this, UserPageFive::class.java).apply {
            putExtra("SELECTED_OCCASION_ID", selectedOccasionId)
            putExtra("SELECTED_OCCASION_IMAGE_URL", selectedOccasionImageUrl) // Pass image URL
        }
        startActivity(intent)
    }
}
