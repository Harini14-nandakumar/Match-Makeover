package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserPageFourAdapter(private val items: List<Pair<Int, String>>) :
    RecyclerView.Adapter<UserPageFourAdapter.ViewHolder>() {

    // ViewHolder class to hold references to views
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewCategoryIcon4)
        val textView: TextView = view.findViewById(R.id.occasionTextView)
    }

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userpage4, parent, false)
        return ViewHolder(view)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.first) // Set the image resource (imageResId)
        holder.textView.text = item.second // Set the name (text)
    }

    // Return the total number of items
    override fun getItemCount(): Int = items.size
}
