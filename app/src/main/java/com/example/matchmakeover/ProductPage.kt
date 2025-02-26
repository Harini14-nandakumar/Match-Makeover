package com.example.matchmakeover

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProductPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        // Find Spinners
        val spinnerGender: Spinner = findViewById(R.id.spinnerGender)
        val spinnerCategories: Spinner = findViewById(R.id.spinnerCategories)
        val spinnerOccasions: Spinner = findViewById(R.id.spinnerOccasions)
        val spinnerColors: Spinner = findViewById(R.id.spinnerColors)

        // Setup Spinners with data
        setupSpinner(spinnerGender, listOf("Select Gender", "Male", "Female", "Kids"))
        setupSpinner(
            spinnerCategories,
            listOf("Select Category", "Shirts", "T-Shirts", "Jackets", "Coats", "Pants", "Trousers")
        )
        setupSpinner(
            spinnerOccasions,
            listOf("Select Occasion", "Formal", "Casual", "Travel", "Festival", "Partywear", "College")
        )
        setupSpinner(
            spinnerColors,
            listOf("Select Color", "Red", "Green", "Blue", "Yellow", "White", "Pink")
        )

        // Set Item Selected Listeners for Spinners
        spinnerGender.onItemSelectedListener = createSpinnerListener("Gender")
        spinnerCategories.onItemSelectedListener = createSpinnerListener("Category")
        spinnerOccasions.onItemSelectedListener = createSpinnerListener("Occasion")
        spinnerColors.onItemSelectedListener = createSpinnerListener("Color")
    }

    private fun setupSpinner(spinner: Spinner, items: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun createSpinnerListener(type: String): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (position != 0) { // Ignore the default "Select" option
                    showToast("$type: $selectedItem selected")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No action needed
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
