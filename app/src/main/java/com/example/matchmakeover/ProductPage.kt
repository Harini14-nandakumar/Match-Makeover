package com.example.matchmakeover

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ProductPage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        findViewById<TextView>(R.id.textViewTitle)

        val spinnerGender: Spinner = findViewById(R.id.spinnergender)
        val buttonMale: Button = findViewById(R.id.buttonMale)
        val buttonFemale: Button = findViewById(R.id.buttonFemale)
        val buttonKids: Button = findViewById(R.id.buttonKids)


        val spinnerCategories: Spinner = findViewById(R.id.spinnerCategories)
        val buttonShirts: Button = findViewById(R.id.buttonShirts)
        val buttonTShirts: Button = findViewById(R.id.buttonTShirts)
        val buttonJackets: Button = findViewById(R.id.buttonJackets)
        val buttonCoats: Button = findViewById(R.id.buttonCoats)
        val buttonPants: Button = findViewById(R.id.buttonPants)
        val buttonTrousers: Button = findViewById(R.id.buttonTrousers)


        val spinnerOccasions: Spinner = findViewById(R.id.spinnerOccasions)
        val buttonFormal: Button = findViewById(R.id.buttonFormal)
        val buttonCasual: Button = findViewById(R.id.buttonCasual)
        val buttonTravel: Button = findViewById(R.id.buttonTravel)
        val buttonFestival: Button = findViewById(R.id.buttonFestival)
        val buttonPartywear: Button = findViewById(R.id.buttonPartywear)
        val buttonCollege: Button = findViewById(R.id.buttonCollege)


        val spinnerColors: Spinner = findViewById(R.id.spinnerColors)
        val buttonRed: Button = findViewById(R.id.buttonRed)
        val buttonGreen: Button = findViewById(R.id.buttonGreen)
        val buttonBlue: Button = findViewById(R.id.buttonBlue)
        val buttonYellow: Button = findViewById(R.id.buttonYellow)
        val buttonWhite: Button = findViewById(R.id.buttonWhite)
        val buttonPink: Button = findViewById(R.id.buttonPink)


        setupSpinner(spinnerGender, listOf("Male", "Female", "Kids"))
        setupSpinner(spinnerCategories, listOf("Shirts", "T-Shirts", "Jackets", "Coats", "Pants", "Trousers"))
        setupSpinner(spinnerOccasions, listOf("Formal", "Casual", "Travel", "Festival", "Partywear", "College"))
        setupSpinner(spinnerColors, listOf("Red", "Green", "Blue", "Yellow", "White", "Pink"))


        buttonMale.setOnClickListener { showToast("Male selected") }
        buttonFemale.setOnClickListener { showToast("Female selected") }
        buttonKids.setOnClickListener { showToast("Kids selected") }

        buttonShirts.setOnClickListener { showToast("Shirts selected") }
        buttonTShirts.setOnClickListener { showToast("T-Shirts selected") }
        buttonJackets.setOnClickListener { showToast("Jackets selected") }
        buttonCoats.setOnClickListener { showToast("Coats selected") }
        buttonPants.setOnClickListener { showToast("Pants selected") }
        buttonTrousers.setOnClickListener { showToast("Trousers selected") }

        buttonFormal.setOnClickListener { showToast("Formal occasion selected") }
        buttonCasual.setOnClickListener { showToast("Casual occasion selected") }
        buttonTravel.setOnClickListener { showToast("Travel occasion selected") }
        buttonFestival.setOnClickListener { showToast("Festival occasion selected") }
        buttonPartywear.setOnClickListener { showToast("Partywear occasion selected") }
        buttonCollege.setOnClickListener { showToast("College occasion selected") }

        buttonRed.setOnClickListener { showToast("Red color selected") }
        buttonGreen.setOnClickListener { showToast("Green color selected") }
        buttonBlue.setOnClickListener { showToast("Blue color selected") }
        buttonYellow.setOnClickListener { showToast("Yellow color selected") }
        buttonWhite.setOnClickListener { showToast("White color selected") }
        buttonPink.setOnClickListener { showToast("Pink color selected") }
    }


    private fun setupSpinner(spinner: Spinner, items: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
