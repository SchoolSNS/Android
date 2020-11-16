package com.example.hischool.adapter

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.module.FeedTime
import com.example.hischool.room.ChatDataBase
import com.example.hischool.view.activity.CommentActivity
import org.w3c.dom.Text

class QuestionAdapter(private val questionList : ArrayList<FeedRecyclerViewData>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_list_item, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questionList[position])
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.findViewById(R.id.question_list_title)
        var content : TextView = itemView.findViewById(R.id.question_list_content)
        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(item: FeedRecyclerViewData) {

            title.text = item.title
            content.text = item.content
            val feedTime = FeedTime()

            itemView.setOnClickListener {
                val intent = (Intent(itemView.context, CommentActivity::class.java))
                intent.putExtra("id", item.id)
                intent.putExtra("ownerName", item.owner.username)
                intent.putExtra("time", feedTime.calFeedTime(item.created_at))
                intent.putExtra("profile", item.owner.profile)
                intent.putExtra("title", item.title)
                intent.putExtra("content", item.content)
                intent.putExtra("heartCount", item.like_count)
                intent.putExtra("commentCount", item.comment_count)
                intent.putExtra("isLike", item.is_liked)
                intent.putExtra("imageUrls", item.image_urls)
                Log.d("TAG", "image url : ${item.image_urls}")
                itemView.context.startActivity(intent)
            }
        }
    }

}