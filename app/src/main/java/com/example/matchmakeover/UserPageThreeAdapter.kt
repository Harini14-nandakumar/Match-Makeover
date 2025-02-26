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

class UserPageThreeAdapter(
    private val items: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<UserPageThreeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewCategoryIcon3)
        val categoryName: TextView = itemView.findViewById(R.id.textViewCategoryName3)

        fun bind(item: Category) {
            val imageUrl = BASE_URL_IMAGE + item.image

            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.kjackets)
                .error(R.drawable.kjackets)
                .into(imageView)

            categoryName.text = item.name

            itemView.setOnClickListener {
                onItemClick(item) // Pass selected item to the listener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userpage3, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
