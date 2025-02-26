package com.example.matchmakeover

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmakeover.api.RetrofitClient.BASE_URL_IMAGE
import com.example.matchmakeover.response.Category

class UserPageTwoAdapter(
    private val items: List<Category>,
    private val onItemClick: (Category) -> Unit
) : RecyclerView.Adapter<UserPageTwoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userpage2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name

        val imageUrl = BASE_URL_IMAGE + item.image

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.western)
            .error(R.drawable.western)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewCategoryIcon2)
        val nameTextView: TextView = view.findViewById(R.id.textViewCategoryName2)
    }
}
