package com.example.matchmakeover

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewColors : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_colors)
        val editText: EditText = findViewById(R.id.editTextText)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val enteredColor = editText.text.toString()

            if (enteredColor.isNotEmpty()) {
                Toast.makeText(this, "Entered Color: $enteredColor", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Please enter a color", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
