package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.LoginRequest
import com.example.matchmakeover.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserLogin : AppCompatActivity() {

    private lateinit var context: Context

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        context = this

        val usernameInput: EditText = findViewById(R.id.editText)
        val passwordInput: EditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.button)
        val signupText: TextView = findViewById(R.id.signup)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                login(username, password)
            }
        }

        signupText.setOnClickListener {
            val intent = Intent(this, SignupPage::class.java)
            startActivity(intent)
        }
    }

    private fun login(username: String, password: String) {
        val request = LoginRequest(username, password)
        val call = RetrofitClient.instance.create(ApiInterface::class.java).userLogin(request)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) =
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.status == true) {
                        Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()

                        // Check the role of the user and navigate accordingly
                        if (loginResponse.role == "admin") { // Assuming `role` is provided in the API response
                            val intent = Intent(context, AdminPage::class.java)
                            startActivity(intent)
                        } else {
                            val intent = Intent(context, UserPageOne::class.java)
                            startActivity(intent)
                        }
                        finish()
                    } else {
                        Toast.makeText(context, loginResponse?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Invalid credentials, please try again.", Toast.LENGTH_SHORT).show()
                }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "Server error ", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
