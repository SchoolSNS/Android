package com.example.hischool.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.FeedRecyclerViewData
import com.example.hischool.view.activity.CommentActivity

class FeedAdapter(val feedList: ArrayList<FeedRecyclerViewData>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        holder.bind(feedList[position])
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val profile = itemView.findViewById<ImageView>(R.id.feed_profile_image)
        val nickname = itemView.findViewById<TextView>(R.id.feed_name_text)
        val time = itemView.findViewById<TextView>(R.id.feed_time_text)
        val title = itemView.findViewById<TextView>(R.id.feed_title_text)
        val question = itemView.findViewById<TextView>(R.id.feed_question_text)
        val count_heart = itemView.findViewById<TextView>(R.id.feed_count_heart_text)
        val count_message = itemView.findViewById<TextView>(R.id.feed_count_message_text)

        fun bind(item : FeedRecyclerViewData)
        {
            profile.setImageResource(item.owner.profile.toInt())
            nickname.text = item.owner.username
            time.text = item.created_at
            title.text = item.title
            question.text = item.text
            count_heart.text = item.like_count.toString()
            count_message.text = item.like_count.toString()

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, CommentActivity::class.java))
            }
        }
    }
}