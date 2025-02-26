package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminLogin : AppCompatActivity() {

    // Declare the EditTexts and Button
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)  // Ensure this matches your XML layout name

        // Initialize views by finding them by ID
        usernameEditText = findViewById(R.id.editText)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.button)

        // Set an OnClickListener for the login button
        loginButton.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        // Get the values entered by the user
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Check if any field is empty
        if (username.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else if (isValidAdmin(username, password)) {
            // Perform successful login action
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

            // Navigate to the AdminPage activity
            val intent = Intent(this, AdminPage::class.java)
            startActivity(intent)
            finish() // Optional: Close this activity to prevent returning to it
        } else {
            // Show error for invalid credentials
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidAdmin(username: String, password: String): Boolean {
        // Replace this logic with actual validation (e.g., server authentication or hardcoded admin credentials)
        val adminUsername = "admin"
        val adminPassword = "admin123"
        return username == adminUsername && password == adminPassword
    }
}
