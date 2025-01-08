package com.example.matchmakeover

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
                // Show a success message or process the input as needed
                Toast.makeText(this, "New Gender Added: $newGender", Toast.LENGTH_SHORT).show()
            }
        }
    }}

