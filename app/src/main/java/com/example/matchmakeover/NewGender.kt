package com.example.matchmakeover

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.GenderRequest
import com.example.matchmakeover.response.NewGenderResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewGender : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var enterButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_gender)  // Ensure this matches your XML layout name

        // Initialize views by finding them by ID
        editText = findViewById(R.id.editTextText0)
        enterButton = findViewById(R.id.button)

        // Set an OnClickListener for the Enter button
        enterButton.setOnClickListener {
            val newGender = editText.text.toString()

            if (newGender.isNotEmpty()) {
                // Create the request body with the entered gender name
                val genderRequest = GenderRequest(gender_name = newGender)

                // Create Retrofit instance and ApiInterface
                val apiInterface = RetrofitClient.retrofitInstance.create(ApiInterface::class.java)

                // Make the API call
                apiInterface.addGender(genderRequest).enqueue(object : Callback<NewGenderResponse> {
                    override fun onResponse(call: Call<NewGenderResponse>, response: Response<NewGenderResponse>) {
                        if (response.isSuccessful && response.body() != null) {
                            val newGenderResponse = response.body()

                            // Show success message from the API response
                            if (newGenderResponse?.status == "success") {
                                Toast.makeText(this@NewGender, newGenderResponse.message, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@NewGender, "Failed: ${newGenderResponse?.message}", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this@NewGender, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<NewGenderResponse>,
                        t: Throwable
                    ) {
                        // Handle failure (network issues, etc.)
                        Toast.makeText(this@NewGender, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                // Show error if the gender input is empty
                Toast.makeText(this, "Please enter a gender", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
