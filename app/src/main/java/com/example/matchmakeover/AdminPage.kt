package com.example.matchmakeover

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_page)

        // Initialize buttons
        val buttonGenders: Button = findViewById(R.id.button2)
        val buttonCategories: Button = findViewById(R.id.button3)
        val buttonOccasions: Button = findViewById(R.id.button4)
        val buttonColors: Button = findViewById(R.id.button5)
        val buttonProducts: Button = findViewById(R.id.button6)


        // Set OnClickListener for buttonGenders
        buttonGenders.setOnClickListener {
            val intent = Intent(this, Gender::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for buttonCategories
        buttonCategories.setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for buttonOccasions
        buttonOccasions.setOnClickListener {
            val intent = Intent(this, Occasions::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for buttonColors
        buttonColors.setOnClickListener {
            val intent = Intent(this, Colors::class.java)
            startActivity(intent)
        }

        // Set OnClickListener for buttonProducts
        buttonProducts.setOnClickListener {
            val intent = Intent(this, ProductPage::class.java)
            startActivity(intent)
        }


    }
}
