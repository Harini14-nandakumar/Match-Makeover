package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmakeover.api.RetrofitClient.BASE_URL_IMAGE
import com.example.matchmakeover.response.Occasions

class UserPageFiveAdapter(
    private val occasions: List<Occasions> // ✅ Fixed: Using a list instead of a single object
) : RecyclerView.Adapter<UserPageFiveAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userpage5, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val occasion = occasions[position] // ✅ Get the occasion at the given position
        holder.descriptionView.text = occasion.name

        val imageUrl = BASE_URL_IMAGE + (occasion.image ?: "") // ✅ Handle null safely
        Glide.with(holder.itemView.context)
            .load(if (!occasion.image.isNullOrEmpty()) imageUrl else R.drawable.tshirts)
            .placeholder(R.drawable.tshirts) // ✅ Placeholder while loading
            .error(R.drawable.pants)         // ✅ Fallback image if loading fails
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = occasions.size // ✅ Fixed: Return correct list size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.outfitImage)
        val descriptionView: TextView = view.findViewById(R.id.outfitText)
    }
}
