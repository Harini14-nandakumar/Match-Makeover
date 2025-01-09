package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.response.Occasions

class OccasionAdapter(
    private val occasions: MutableList<Occasions>
) : RecyclerView.Adapter<OccasionAdapter.OccasionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccasionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_occasions, parent, false)
        return OccasionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OccasionViewHolder, position: Int) {
        val occasions = occasions[position]
        holder.getOccasionName().text = occasions.toString()
        holder.itemView.setOnClickListener { TODO(occasions.toString()) }
    }

    override fun getItemCount(): Int = occasions.size

    inner class OccasionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun getOccasionName(): TextView = itemView.findViewById(R.id.tvCategoryName)
    }
}
