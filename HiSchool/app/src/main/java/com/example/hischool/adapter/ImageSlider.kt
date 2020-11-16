package com.example.hischool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hischool.R
import com.example.hischool.data.SliderData
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSlider(private val imageList : ArrayList<SliderData>, private val context: Context) :  SliderViewAdapter<ImageSlider.ViewHolder>(){

    override fun getCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_image_slider, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, position: Int) {
        viewHolder?.bind(imageList[position])
    }

    inner class ViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView){
        var image : ImageView = itemView.findViewById(R.id.slide_image)
        fun bind(item: SliderData) {
            Glide.with(itemView)
                .load(item.ImageUrl)
                .fitCenter()
                .dontTransform()
                .into(image)
        }
    }
}