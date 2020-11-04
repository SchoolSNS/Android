package com.example.hischool.adapter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import kotlinx.android.synthetic.main.comment_image_preview_item.view.*

class CommentImagePreViewAdapter(private val commentImageList : ArrayList<Bitmap>, private val context: Context) :
    RecyclerView.Adapter<CommentImagePreViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_image_preview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(commentImageList[position])
            .transform(CenterCrop(), RoundedCorners(25))
            .into(holder.imageViewList)

    }

    override fun getItemCount(): Int {
        Log.d("TAG", commentImageList.size.toString())
        return commentImageList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageViewList: ImageView = itemView.preview_image_view
    }
}