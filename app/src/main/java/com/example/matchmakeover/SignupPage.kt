package com.example.matchmakeover

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

    lateinit var binding: ActivitySignupPageBinding
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupPageBinding.inflate(layoutInflater);
        setContentView(binding.root)

        context = this

        binding.button.setOnClickListener {
            val name = binding.name.text.toString()
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val email = binding.email.text.toString()

            // Validate the inputs
            if (name.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT)
                    .show()
            } else {

                signup(name,username,password,email)
//                // Mock signup logic
//                Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()
//
//                // Navigate to the login page or home page
//                val intent = Intent(this, UserPageOne::class.java)
//                startActivity(intent)
//                finish() // Close the current activity
            }
        }


    }

    private fun signup(name: String, username: String, password: String, email: String) {

        val request = UserRequest(name,username,email,password,"user")
        val call = RetrofitClient.instance.create(ApiInterface::class.java).signUpUser(request)

        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if(response.isSuccessful){
                    if (response.body()?.status == "success"){
                        Toast.makeText(context, response.body()!!.message,Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, response.body()!!.message,Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(context, "Server error ",Toast.LENGTH_SHORT).show()
            }

        })
    }
}
