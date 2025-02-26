package com.example.matchmakeover

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.NewOccasionsRequest
import com.example.matchmakeover.response.NewOccasionsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewOccasions : AppCompatActivity() {

    private lateinit var editTextOccasion: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_occasions)

        // Initialize views
        editTextOccasion = findViewById(R.id.editTextText)
        buttonSubmit = findViewById(R.id.button)

        // Set up button click listener
        buttonSubmit.setOnClickListener {
            val occasionName = editTextOccasion.text.toString().trim()

            if (occasionName.isNotEmpty()) {
                addOccasion(occasionName)
            } else {
                Toast.makeText(this, "Please enter an occasion", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addOccasion(occasionName: String) {
        // Create request body
        val occasionRequest = NewOccasionsRequest(occasionName)

        // Create Retrofit instance and API interface
        val apiInterface = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)

        // Make API call
        apiInterface.addOccasion(occasionRequest).enqueue(object : Callback<NewOccasionsResponse> {
            override fun onResponse(call: Call<NewOccasionsResponse>, response: Response<NewOccasionsResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    // Use Elvis operator to provide a default value if message is null
                    val message = body?.message ?: "Success"
                    if (body != null && body.status == "success") {
                        // Show success message

                    } else {
                        // Show failure message
                        Toast.makeText(this@NewOccasions, "Failed: $message", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Show error message
                    Toast.makeText(this@NewOccasions, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewOccasionsResponse>, t: Throwable) {
                // Handle network failure
                val errorMessage = t.message ?: "Unknown error"
                Toast.makeText(this@NewOccasions, "Network Error: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
