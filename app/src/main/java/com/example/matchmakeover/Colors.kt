package com.example.matchmakeover

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
import com.example.matchmakeover.responsepackage.Color
import com.example.matchmakeover.responsepackage.ColorResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Colors : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var colorsAdapter: ColorsAdapter
    private var colorsList = ArrayList<Color>()

    private lateinit var addButton: ImageButton
    private lateinit var titleTextView: TextView

    // Static list for colors if the API fails
//    private val defaultColors = listOf(
//        Colors("Red"),
//        Colors("Blue"),
//        Colors("White"),
//        Colors("Green"),
//        Colors("Pink"),
//        Colors("Black")
//    )

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
        titleTextView.text = "Color"

        // Fetch the colors from the API
        fetchColors()
    }

    private fun fetchColors() {
        // Use the API method to fetch colors
        val call = RetrofitClient.instance.create(ApiInterface::class.java).fetchColors()

        call.enqueue(object : Callback<ColorResponse> {
            override fun onResponse(call: Call<ColorResponse>, response: Response<ColorResponse>) {
                if (response.isSuccessful) {
                    val colorResponse = response.body()
                    if (colorResponse != null && colorResponse.status == "success") {
                        colorsList.clear()
                        response.body()!!.colors.forEach({ color ->
                            colorsList.add(color)
                        })
                        colorsAdapter.notifyDataSetChanged()
                    } else {
                        showToast("Failed to fetch colors, using defaults.")
//                        useDefaultColors()
                    }
                } else {
                    showToast("Error fetching colors, using defaults.")
//                    useDefaultColors()
                }
            }

            override fun onFailure(call: Call<ColorResponse>, t: Throwable) {
                showToast("Error: ${t.message}, using defaults.")
//                useDefaultColors()
            }
        })
    }

//    private fun useDefaultColors() {
//        colorsList.clear()
//        colorsList.addAll(defaultColors)
//        colorsAdapter.notifyDataSetChanged()
//    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
