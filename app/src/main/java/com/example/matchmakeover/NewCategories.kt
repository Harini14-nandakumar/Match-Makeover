package com.example.matchmakeover

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewCategories : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_categories)

        val editText = findViewById<EditText>(R.id.editTextText1)
        val enterButton = findViewById<Button>(R.id.button)

        enterButton.setOnClickListener {
            val enteredCategory = editText.text.toString().trim()

            if (enteredCategory.isNotEmpty()) {
                Toast.makeText(this, "Category Entered: $enteredCategory", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Please enter a category", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
