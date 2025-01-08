package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserPageFiveAdapter(private val outfits: List<UserPageFive.UserPageFive>) :
    RecyclerView.Adapter<UserPageFiveAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.outfitImage)
        val descriptionView: TextView = itemView.findViewById(R.id.outfitText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userpage5, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val outfit = outfits[position]
        holder.imageView.setImageResource(/* resId = */ outfit.imageRes)
        holder.descriptionView.text = outfit.description
    }

    override fun getItemCount(): Int = outfits.size
}
