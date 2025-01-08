package com.example.matchmakeover

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NewOccasions : AppCompatActivity() {

    private lateinit var editTextOccasion: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_occasions)


        editTextOccasion = findViewById(R.id.editTextText)
        buttonSubmit = findViewById(R.id.button)


        buttonSubmit.setOnClickListener {
            val occasion = editTextOccasion.text.toString().trim()

            if (occasion.isEmpty()) {
                Toast.makeText(this, "Please enter an occasion", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this, "Occasion '$occasion' added successfully", Toast.LENGTH_SHORT).show()


                editTextOccasion.text.clear()
            }
        }
    }
}
