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

class CategoriesAdapter(private val categories: MutableList<Category>) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
        val ivCategories : ImageView = view.findViewById(R.id.ivCategoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categories, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.tvCategoryName.text = categories[position].name

        val imageUrl = BASE_URL_IMAGE + categories[position].image

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.casualt)
            .error(R.drawable.casualt)
            .into(holder.ivCategories)

    }

    override fun getItemCount(): Int = categories.size
}
