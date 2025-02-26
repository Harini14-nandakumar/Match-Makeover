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

    private var genderId:String =""
    private var categoryId:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colorgrid)

        val titleText: TextView = findViewById(R.id.titleText)
        val selectedColorText: TextView = findViewById(R.id.selectedColorText)
        val colorGrid: GridView = findViewById(R.id.colorGrid)
        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton2)


        genderId = intent.getStringExtra("SELECTED_GENDER_ID") ?: ""
        categoryId = intent.getStringExtra("SELECTED_CATEGORY_ID") ?: ""

        // Define a list of colors
        val colors = arrayOf(
            // Reds
            "#FF0000", "#DC143C", "#B22222", "#8B0000", "#FF4500", "#FF6347", "#FA8072", "#E32636", "#D70040",
            "#A52A2A", "#800000", "#9B111E", "#C41E3A", "#D2042D",

            // Greens
            "#00FF00", "#32CD32", "#228B22", "#006400", "#7CFC00", "#ADFF2F", "#90EE90", "#2E8B57", "#3CB371",
            "#556B2F", "#6B8E23", "#008000", "#00A36C", "#4CBB17", "#9ACD32",

            // Blues
            "#0000FF", "#1E90FF", "#4169E1", "#4682B4", "#5F9EA0", "#00CED1", "#87CEEB", "#6495ED", "#7B68EE",
            "#6A5ACD", "#483D8B", "#191970", "#00008B", "#008B8B", "#5D8AA8",

            // Yellows & Golds
            "#FFFF00", "#FFD700", "#DAA520", "#B8860B", "#FFFACD", "#F0E68C", "#EEE8AA", "#FFDAB9", "#F5DEB3",
            "#FFEC8B", "#F4A460", "#EEDC82", "#E3CF57", "#D4AF37",

            // Purples
            "#FF00FF", "#800080", "#8A2BE2", "#9370DB", "#BA55D3", "#DA70D6", "#D8BFD8", "#6A0DAD", "#9932CC",
            "#9400D3", "#4B0082", "#8B008B", "#DDA0DD", "#B19CD9", "#9F00FF",

            // Browns & Earth Tones
            "#8B4513", "#D2691E", "#CD853F", "#F4A460", "#DEB887", "#FFE4C4", "#C19A6B", "#8D5524", "#A67B5B",
            "#D2B48C", "#B5651D", "#5D3A1A", "#9C661F", "#654321",

            // Oranges
            "#FFA500", "#FF8C00", "#FF7F50", "#E9967A", "#FA8072", "#FF4500", "#FF5E00", "#FF4500", "#FF6F61",
            "#E97451", "#D2691E", "#FF7538", "#EC5800", "#CD5C5C",

            // Greys & Neutrals
            "#808080", "#696969", "#A9A9A9", "#C0C0C0", "#D3D3D3", "#F5F5F5", "#B0C4DE", "#778899", "#708090",
            "#2F4F4F", "#BEBEBE", "#E0E0E0", "#DCDCDC", "#E5E4E2",

            // Pinks
            "#FFC0CB", "#FF69B4", "#FF1493", "#DB7093", "#C71585", "#FFB6C1", "#FFA6C9", "#E75480", "#DE3163",
            "#D87093", "#FF91A4", "#F4C2C2", "#E30B5C", "#FC0FC0",

            // Whites & Blacks
            "#FFFFFF", "#F8F8FF", "#FAF0E6", "#F5F5DC", "#000000", "#2C2C2C", "#1C1C1C", "#121212", "#4A4A4A",

            // Teals & Cyan
            "#008080", "#00FFFF", "#40E0D0", "#48D1CC", "#20B2AA", "#AFEEEE", "#5F9EA0", "#008B8B", "#00CED1",
            "#B2FFFF", "#00A7A7", "#2E8B57", "#5F9F9F", "#30D5C8",

            // Miscellaneous & Exotic Colors
            "#FFDAB9", "#B22222", "#DCD0FF", "#00BFFF", "#1C39BB", "#FF4F00", "#7FFFD4", "#D0F0C0", "#89CFF0",
            "#FFFAF0", "#E6E6FA", "#FFF0F5", "#FFDEAD", "#8FBC8F"
        )


        // Set up the GridView adapter
        colorGrid.adapter = ColorgridAdapter(this, colors)

        // Handle GridView item clicks
        colorGrid.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedColor = colors[position]
            selectedColorText.text = "You Chose: $selectedColor"
            selectedColorText.setTextColor(Color.parseColor(selectedColor))
        }

//        // Navigate to UserPageFive when selectedColorText is clicked
//        selectedColorText.setOnClickListener {
//            val intent = Intent(this, UserPageFour::class.java)
//            startActivity(intent)
//        }

        // Set up FloatingActionButton navigation
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, UserPageFour::class.java)
            intent.putExtra("gender_id",""+genderId)
            intent.putExtra("category_id",""+categoryId)
            startActivity(intent)
        }
    }
}
