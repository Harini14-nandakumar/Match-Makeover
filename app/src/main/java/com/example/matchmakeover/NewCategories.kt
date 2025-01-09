package com.example.matchmakeover

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.NewCategoriesResponse
import com.example.matchmakeover.response.NewCategoriesRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewCategories : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var enterButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_categories) // Ensure this matches your XML layout name

        // Initialize views
        editText = findViewById(R.id.editTextText1)
        enterButton = findViewById(R.id.button)

        // Set up button click listener
        enterButton.setOnClickListener {
            val newCategory = editText.text.toString()

            if (newCategory.isNotEmpty()) {
                addCategory(newCategory)
            } else {
                Toast.makeText(this, "Please enter a category", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addCategory(categoryName: String) {
        // Create the request body
        val categoryRequest = NewCategoriesRequest(categoryName)

        // Create Retrofit instance and ApiInterface
        val apiInterface = RetrofitClient.instance.create(ApiInterface::class.java)

        // Make the API call
        apiInterface.addCategory(categoryRequest).enqueue(object : Callback<NewCategoriesResponse> {
            override fun onResponse(call: Call<NewCategoriesResponse>, response: Response<NewCategoriesResponse>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.status == "success") {
                        // Show success message
                        Toast.makeText(this@NewCategories, body.message, Toast.LENGTH_SHORT).show()
                    } else {
                        // Show failure message from the API response
                        Toast.makeText(this@NewCategories, "Failed: ${body?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Show error message if response is not successful
                    Toast.makeText(this@NewCategories, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewCategoriesResponse>, t: Throwable) {
                // Handle network failure
                Toast.makeText(this@NewCategories, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
