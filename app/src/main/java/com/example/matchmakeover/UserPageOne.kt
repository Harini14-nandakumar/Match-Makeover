package com.example.matchmakeover

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.api.ApiInterface
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Category
import com.example.matchmakeover.response.ClothingResponse
import com.example.matchmakeover.response.Gender
import com.example.matchmakeover.response.GenderResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPageOne : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: UserPageOneAdapter
    private val items = mutableListOf<Category>()

    private lateinit var genderSpinner: Spinner
    private val genderList = ArrayList<Gender>()

    private lateinit var floatingButton: FloatingActionButton

    private var selectedGenderId: String? = null
    private var selectedCategoryId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page1)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCategories)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        // Initialize adapter with click listener
        itemAdapter = UserPageOneAdapter(items) { selectedCategory: Category ->
            selectedCategoryId = selectedCategory.id.toString() // Store selected category ID
        }
        recyclerView.adapter = itemAdapter

        // Initialize Floating Action Button
        floatingButton = findViewById(R.id.floatingActionButton2)

        // Set up click listener for FAB
        floatingButton.setOnClickListener {
            if (selectedGenderId.isNullOrEmpty()) {
                showToast("Please select a Gender")
            } else if (selectedCategoryId.isNullOrEmpty()) {
                showToast("Please select a Category")
            } else {
                navigateToPage(Colorgrid::class.java) // Navigate to next page
            }
        }

        // Fetch genders
        fetchGenders()
    }

    private fun setupGenderSpinner() {
        genderSpinner = findViewById(R.id.spinnerCategory)

        // Extract only gender names for the spinner
        val genderNames = genderList.map { it.name }

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderNames)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = spinnerAdapter

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedGender = genderList[position]
                selectedGenderId = selectedGender.id.toString() // Store gender ID as String

                // Fetch categories based on selected gender
                when (selectedGender.name.lowercase()) {
                    "female" -> {
                        fetchCategories("female") // Fetch female categories
                        navigateToPage(UserPageTwo::class.java)  // Navigate to UserPageTwo
                    }
                    "male" -> {
                        fetchCategories("male")  // Fetch male categories
                    }
                    "kids" -> {
                        fetchCategories("kids")  // Fetch kids categories
                        navigateToPage(UserPageThree::class.java)  // Navigate to UserPageThree
                    }
                    "all", "select gender" -> fetchCategories("all")  // Show all categories
                    else -> fetchCategories("all")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                fetchCategories("all") // Default to all categories
            }
        }
    }

    private fun fetchGenders() {
        val call = RetrofitClient.retrofitInstance.create(ApiInterface::class.java).fetchGenders()
        call.enqueue(object : Callback<GenderResponse> {
            override fun onResponse(call: Call<GenderResponse>, response: Response<GenderResponse>) {
                if (response.isSuccessful) {
                    val genderResponse = response.body()
                    if (genderResponse != null && genderResponse.status == "success") {
                        genderList.clear()
                        genderList.addAll(genderResponse.genders)

                        // Refresh spinner with new data
                        setupGenderSpinner()
                    } else {
                        showToast("Failed to load genders")
                    }
                } else {
                    showToast("Error fetching genders: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GenderResponse>, t: Throwable) {
                Log.e("UserPageOne", "API Error: ${t.message}")
                showToast("Failed to connect to server. Please try again later.")
            }
        })
    }

    private fun fetchCategories(gender: String = "all") {
        val call = RetrofitClient.retrofitInstance.create(ApiInterface::class.java).fetchCategories(gender)
        call.enqueue(object : Callback<ClothingResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ClothingResponse>, response: Response<ClothingResponse>) {
                if (response.isSuccessful) {
                    val categoryResponse = response.body()
                    if (categoryResponse != null && categoryResponse.status == "success") {
                        items.clear()
                        items.addAll(categoryResponse.categories)
                        itemAdapter.notifyDataSetChanged()
                    } else {
                        showToast("Failed to load categories")
                    }
                } else {
                    showToast("Error fetching categories: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ClothingResponse>, t: Throwable) {
                Log.e("UserPageOne", "API Error: ${t.message}")
                showToast("Failed to connect to server. Please try again later.")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToPage(targetClass: Class<*>) {
        val intent = Intent(this, targetClass).apply {
            putExtra("SELECTED_GENDER_ID", selectedGenderId)
            putExtra("SELECTED_CATEGORY_ID", selectedCategoryId)
        }
        startActivity(intent)
    }
}
