package com.example.hischool.adapter

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.data.NotificationRecyclerViewData
import com.example.hischool.module.FeedTime
import com.example.hischool.view.activity.CommentActivity

class NotificationAdapt (val notificationList: ArrayList<NotificationRecyclerViewData>) : RecyclerView.Adapter<NotificationAdapt.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(notificationList[position])
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val feedTime = FeedTime()
        val contents = itemView.findViewById<TextView>(R.id.notification_text)
        val profile : ImageView = itemView.findViewById(R.id.notification_profile)
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(item : NotificationRecyclerViewData)
        {
            contents.text = item.notification_title
            Glide.with(itemView)
                .load(item.post.owner.profile)
                .transform(CenterCrop(), RoundedCorners(10000))
                .into(profile)

            itemView.setOnClickListener {
                val intent = (Intent(itemView.context, CommentActivity::class.java))
                intent.putExtra("id", item.post.id)
                intent.putExtra("ownerName", item.post.owner.username)
                intent.putExtra("time", feedTime.calFeedTime(item.post.created_at))
                intent.putExtra("profile", item.post.owner.profile)
                intent.putExtra("title", item.post.title)
                intent.putExtra("content", item.post.content)
                intent.putExtra("heartCount", item.post.like_count)
                intent.putExtra("commentCount", item.post.comment_count)
                intent.putExtra("isLike", item.post.is_liked)
                intent.putExtra("imageUrls", item.post.image_urls)
                Log.d("TAG", "image url : ${item.post.image_urls}")
                itemView.context.startActivity(intent)
            }
        }

    }

}