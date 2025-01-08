package com.example.matchmakeover

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Colorgrid : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colorgrid)

        findViewById<TextView>(R.id.titleText)
        val selectedColorText: TextView = findViewById(R.id.selectedColorText)
        val colorGrid: GridView = findViewById(R.id.colorGrid)

        val colors = arrayOf(
            "#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#FF00FF", "#00FFFF",
            "#FFA500", "#A52A2A", "#800080", "#808080",
            "#FFC0CB", "#000000", "#FFFFFF", "#008080", "#800000",
            "#FF6347", "#90EE90", "#7B68EE", "#8B4513", "#4682B4"
        )

        colorGrid.adapter = ColorgridAdapter(this, colors)

        colorGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedColor = colors[position]
            selectedColorText.text = "You Chose: $selectedColor"
            selectedColorText.setTextColor(Color.parseColor(selectedColor))
        }
    }
}
