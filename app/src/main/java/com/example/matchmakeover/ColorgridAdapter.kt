package com.example.matchmakeover

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class ColorgridAdapter(private val context: Context, private val colors: Array<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return colors.size
    }

    override fun getItem(position: Int): Any {
        return colors[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(60, 60)
        } else {
            imageView = convertView as ImageView
        }
        imageView.setBackgroundColor(Color.parseColor(colors[position]))
        return imageView
    }
}
