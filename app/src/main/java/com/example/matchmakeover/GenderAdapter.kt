package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GenderAdapter(private val genderList: List<String>) :
    RecyclerView.Adapter<GenderAdapter.GenderViewHolder>() {

    class GenderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvGender: TextView = view.findViewById(R.id.tvCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gender, parent, false)
        return GenderViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        holder.tvGender.text = genderList[position]
    }

    override fun getItemCount(): Int = genderList.size
}
