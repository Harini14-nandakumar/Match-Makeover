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
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Gender
import com.example.matchmakeover.response.GenderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Gender : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var genderAdapter: GenderAdapter
    private val genderList = mutableListOf<Gender>()

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        // Initialize views
        recyclerView = findViewById(R.id.Genders)
        addButton = findViewById(R.id.ivAddIcon)
        titleTextView = findViewById(R.id.tvTitle)

        // Set up RecyclerView with the gender list
        recyclerView.layoutManager = LinearLayoutManager(this)
        genderAdapter = GenderAdapter(genderList)
        recyclerView.adapter = genderAdapter

        // Set up the Add button click listener
        addButton.setOnClickListener {
            Toast.makeText(this, "Add gender clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NewGender::class.java)
            startActivity(intent)
        }

        // Fetch the gender list from the API
        fetchGenders()

        // Set the title
        titleTextView.text = "Select Gender"
    }

    // Method to fetch gender list from the API
    private fun fetchGenders() {
        val call = RetrofitClient.instance.gender() // Call the Retrofit API to fetch genders

        call.enqueue(object : Callback<GenderResponse> {
            override fun onResponse(call: Call<GenderResponse>, response: Response<GenderResponse>) {
                if (response.isSuccessful) {
                    val genderResponse = response.body()
                    if (genderResponse != null && genderResponse.status == "success") {
                        genderList.clear()
                        genderList.addAll(genderResponse.genders)
                        genderAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this@Gender, "Failed to fetch genders", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@Gender, "Error fetching genders", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GenderResponse>, t: Throwable) {
                Toast.makeText(this@Gender, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
