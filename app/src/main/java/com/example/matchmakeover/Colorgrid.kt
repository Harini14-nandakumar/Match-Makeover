package com.example.matchmakeover

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Colorgrid : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colorgrid)

        val titleText: TextView = findViewById(R.id.titleText)
        val selectedColorText: TextView = findViewById(R.id.selectedColorText)
        val colorGrid: GridView = findViewById(R.id.colorGrid)
        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)

        // Define a list of colors
        val colors = arrayOf(
            "#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF",
            "#FFA500", "#A52A2A", "#800080", "#808080",
            "#FFC0CB", "#000000", "#FFFFFF", "#008080", "#800000",
            "#FF6347", "#90EE90", "#7B68EE", "#8B4513", "#4682B4"
        )

        // Set up the GridView adapter
        colorGrid.adapter = ColorgridAdapter(this, colors)

        // Handle GridView item clicks
        colorGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedColor = colors[position]
            selectedColorText.text = "You Chose: $selectedColor"
            selectedColorText.setTextColor(Color.parseColor(selectedColor))
        }

        // Set up FloatingActionButton navigation
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, UserPageFour::class.java)
            startActivity(intent)
        }
    }
}
