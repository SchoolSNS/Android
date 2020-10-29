package com.example.hischool.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.data.comment.CommentRecyclerViewData

class CommentAdapter(val commentArrayList: ArrayList<CommentRecyclerViewData>, private val context: Context) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentArrayList[position])
    }

    override fun getItemCount(): Int {
        Log.d("TAG", commentArrayList.size.toString())
        return commentArrayList.size
    }

    class ViewHolder (itemView: View, context: Context) : RecyclerView.ViewHolder(itemView){
        val name  = itemView.findViewById<TextView>(R.id.comment_name)
        val message = itemView.findViewById<TextView>(R.id.comment_message)
        val mContext = context
        val imageList : ImageView = itemView.findViewById(R.id.comment_image_list)
        val imageList2 : ImageView = itemView.findViewById(R.id.comment_image_list2)

        fun bind(item : CommentRecyclerViewData)
        {
            imageList.visibility = View.GONE
            imageList2.visibility = View.GONE
            if(item.image_urls.size == 1)
            {
                imageList.visibility = View.VISIBLE
                setOneImage(item)
            }
            else if(item.image_urls.size == 2)
            {
                imageList.visibility = View.VISIBLE
                imageList2.visibility = View.VISIBLE
                setAllImage(item)
            }
            Log.d("TAG", item.owner.username)
            name.text = item.owner.username
            message.text = item.content
        }

        private fun setOneImage(item: CommentRecyclerViewData)
        {
            Glide.with(mContext)
                .load(item.image_urls[0])
                .transform(CenterCrop(), RoundedCorners(25))
                .into(imageList)
        }

        private fun setAllImage(item: CommentRecyclerViewData)
        {
            setOneImage(item)
            Glide.with(mContext)
                .load(item.image_urls[1])
                .transform(CenterCrop(), RoundedCorners(25))
                .into(imageList2)
        }

    }
}