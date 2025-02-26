package com.example.matchmakeover

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmakeover.api.RetrofitClient
import com.example.matchmakeover.response.Occasions

class UserPageFourAdapter(
    private val occasions: List<Occasions>,
    private val onOccasionSelected: (Occasions) -> Unit // Click listener
) : RecyclerView.Adapter<UserPageFourAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.imageViewCategoryIcon4)
        private val textView: TextView = itemView.findViewById(R.id.occasionTextView)

        fun bind(occasion: Occasions) {
            textView.text = occasion.name
            val imageUrl = RetrofitClient.BASE_URL_IMAGE + (occasion.image ?: "")
            Log.d("UserPageFourAdapter", "Loading Image URL: $imageUrl")

            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.casualt) // Placeholder while loading
                .error(R.drawable.casualt) // Image to show if loading fails
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_userpage4, parent, false)



        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val occasion = occasions[position]
        holder.bind(occasion)
        holder.itemView.setOnClickListener { onOccasionSelected(occasion) }
    }

    override fun getItemCount(): Int = occasions.size
}
