package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserPageTwoAdapter(private val items: List<UserPageTwo.ClothingItem>) :
    RecyclerView.Adapter<UserPageTwoAdapter.ViewHolder>() {

    // ViewHolder class to hold references to views
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewCategoryIcon2) // Ensure the ID matches
        val textView: TextView = view.findViewById(R.id.textViewCategoryName2)    // Ensure the ID matches
    }

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate item layout and return ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userpage2, parent, false)  // Ensure this layout file exists
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId) // Set the image resource
        holder.textView.text = item.name // Set the name of the category
    }

    // Return the total number of items
    override fun getItemCount(): Int = items.size
}
