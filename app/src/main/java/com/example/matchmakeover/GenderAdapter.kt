package com.example.matchmakeover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmakeover.response.Gender

class GenderAdapter(private val genderList: MutableList<Gender>) : RecyclerView.Adapter<GenderAdapter.GenderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gender, parent, false)
        return GenderViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenderViewHolder, position: Int) {
        val gender = genderList[position]
        holder.genderNameTextView.text = gender.name
    }

    override fun getItemCount(): Int = genderList.size

    class GenderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genderNameTextView: TextView = itemView.findViewById(R.id.tvCategoryName)
    }}
