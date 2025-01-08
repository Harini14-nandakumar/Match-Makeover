package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserPageOneAdapter(private val categories: List<UserPageOne.Category>) :
    RecyclerView.Adapter<UserPageOneAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userpage1, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories[position]
        holder.bind(item)
    }

    override fun getItemCount() = categories.size

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewCategoryIcon)
        private val textView: TextView = itemView.findViewById(R.id.textViewCategoryName)

        fun bind(category: UserPageOne.Category) {
            imageView.setImageResource(category.imageRes) // Set the image resource
            textView.text = category.name                 // Set the category name
        }
    }
}
