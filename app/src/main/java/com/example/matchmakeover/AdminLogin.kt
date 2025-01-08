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
            // Get the values entered by the user
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()


            // Check if any field is empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Perform login logic here (e.g., validate credentials)
                // For now, just show a success message
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Navigate to the AdminPage activity after successful login
                val intent = Intent(this, AdminPage::class.java)
                startActivity(intent)
            }
        }}}
