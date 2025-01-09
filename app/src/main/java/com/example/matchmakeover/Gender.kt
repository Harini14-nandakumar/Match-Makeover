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
import com.example.matchmakeover.response.Gender
import com.example.matchmakeover.response.GenderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Gender : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var genderAdapter: GenderAdapter
    private val genderList = mutableListOf<Gender>() // Dynamic list for genders
    private val defaultGenders = listOf(Gender(0.toString(), "Male"), Gender(0.toString(), "Female")) // Static fallback list

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
        titleTextView.text = "Genders"
    }

    private fun fetchGenders() {
        val call = RetrofitClient.instance.create(ApiInterface::class.java).fetchgender()

        call.enqueue(object : Callback<GenderResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<GenderResponse>, response: Response<GenderResponse>) {
                if (response.isSuccessful) {
                    val genderResponse = response.body()
                    if (genderResponse != null && genderResponse.status == "success") {
                        genderList.clear()
                        genderList.addAll(genderResponse.genders)
                        genderAdapter.notifyDataSetChanged()
                    } else {
                        showToast("No data from API, using defaults")
                        useDefaultGenders()
                    }
                } else {
                    showToast("API error, using defaults")
                    useDefaultGenders()
                }
            }

            override fun onFailure(call: Call<GenderResponse>, t: Throwable) {
                showToast("Network error, using defaults")
                useDefaultGenders()
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun useDefaultGenders() {
        genderList.clear()
        genderList.addAll(defaultGenders)
        genderAdapter.notifyDataSetChanged()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        fetchGenders()
    }
}
