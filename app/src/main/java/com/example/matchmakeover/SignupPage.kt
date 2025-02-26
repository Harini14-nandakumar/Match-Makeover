package com.example.matchmakeover

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.databinding.ActivitySignupPageBinding
import com.example.matchmakeover.response.UserRequest
import com.example.matchmakeover.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupPage : AppCompatActivity() {

    private lateinit var binding: ActivitySignupPageBinding
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view using ViewBinding
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        // Set click listener for signup button
        binding.button.setOnClickListener {
            val name = binding.name.text.toString()
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val email = binding.email.text.toString()

            // Validate the inputs
            when {
                name.isEmpty() -> {
                    Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
                username.isEmpty() -> {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                }
                email.isEmpty() -> {
                    Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
                }
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    signup(name, username, password, email)
                }
            }
        }
    }

    private fun signup(name: String, username: String, password: String, email: String) {
        val request = UserRequest(name, username, email, password, "user")
        val call = RetrofitClient.retrofitInstance.create(ApiInterface::class.java).signUpUser(request)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status == "success") {
                        Toast.makeText(context, responseBody.message, Toast.LENGTH_SHORT).show()
                        Log.d("Signup", "Navigating to com.example.matchmakeover.UserPageOne")

                        // Navigate to com.example.matchmakeover.UserPageOne
                        val intent = Intent(this@SignupPage, UserPageOne::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(context, responseBody?.message ?: "Signup failed", Toast.LENGTH_SHORT).show()
                        Log.e("Signup", "Error: ${responseBody?.message}")
                    }
                } else {
                    Toast.makeText(context, "Unexpected server error", Toast.LENGTH_SHORT).show()
                    Log.e("Signup", "Server response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "Network or server error", Toast.LENGTH_SHORT).show()
                Log.e("Signup", "Failure: ${t.message}", t)
            }
        })
    }
}
