package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.response.Color

class ColorsAdapter(private val colorList: MutableList<Color>) :
    RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    // ViewHolder class to hold the view reference
    inner class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvColor: TextView = itemView.findViewById(R.id.tvCategoryName) // Ensure this ID exists in item_colors.xml
    }

    // Create a new view holder and return it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        // Inflate the layout for each item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_colors, parent, false)
        return ColorViewHolder(view)
    }

    // Bind the data to the view holder
    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        // Set the color text to the TextView
        holder.tvColor.text = colorList[position].toString()
    }

    // Return the total number of items in the list
    override fun getItemCount(): Int = colorList.size
}
