package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmakeover.api.RetrofitClient.BASE_URL_IMAGE
import com.example.matchmakeover.response.Category

class UserPageOneAdapter(
    private val items: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<UserPageOneAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView:ImageView = itemView.findViewById(R.id.ivCategoryImage)
        val categoryName:TextView = itemView.findViewById(R.id.tvCategoryName)

        fun bind(item: Category) {
            // Bind data to views (e.g., set image, name)
            // Example: itemView.findViewById<TextView>(R.id.itemName).text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]


        val imageUrl = BASE_URL_IMAGE + item.image

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.casualt)
            .error(R.drawable.casualt)
            .into(holder.imageView)

        holder.categoryName.text = item.name

        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClick(item) // Pass selected item to the listener
        }
    }

    override fun getItemCount(): Int = items.size
}
