package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserPageFiveAdapter(
    private val outfits: List<UserPageFive>
) : RecyclerView.Adapter<UserPageFiveAdapter.OutfitViewHolder>() {

    // ViewHolder to bind the layout items
    class OutfitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val outfitImage: ImageView = itemView.findViewById(R.id.outfitImage)
        val outfitText: TextView = itemView.findViewById(R.id.outfitText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutfitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userpage5, parent, false) // Ensure this matches your item layout
        return OutfitViewHolder(view)
    }

    override fun onBindViewHolder(holder: OutfitViewHolder, position: Int) {
        val outfit = outfits[position]
        holder.outfitImage.setImageResource(outfit.imageRes)
        holder.outfitText.text = outfit.description
    }

    override fun getItemCount(): Int {
        return outfits.size
    }
}
