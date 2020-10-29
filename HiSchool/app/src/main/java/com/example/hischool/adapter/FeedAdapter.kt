package com.example.hischool.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.view.activity.CommentActivity
import org.w3c.dom.Text

class FeedAdapter(private val feedList : ArrayList<FeedRecyclerViewData>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
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
        val proFile = itemView.findViewById<ImageView>(R.id.feed_profile_image)
        val nickName = itemView.findViewById<TextView>(R.id.feed_name_text)
        val time = itemView.findViewById<TextView>(R.id.feed_time_text)
        val title = itemView.findViewById<TextView>(R.id.feed_title_text)
        val question = itemView.findViewById<TextView>(R.id.feed_question_text)
        val countHeart = itemView.findViewById<TextView>(R.id.feed_count_heart_text)
        val countMessage = itemView.findViewById<TextView>(R.id.feed_count_message_text)
        val comment1 : ConstraintLayout = itemView.findViewById(R.id.feed_comment1)
        val comment2 : ConstraintLayout = itemView.findViewById(R.id.feed_comment2)
        val comment1Image : ImageView = itemView.findViewById(R.id.feed_comment_profile_image)
        val comment1Name : TextView = itemView.findViewById(R.id.feed_comment_name)
        val comment1Text : TextView = itemView.findViewById(R.id.feed_comment_text)
        val comment2Image : ImageView = itemView.findViewById(R.id.feed_comment_profile_image2)
        val comment2Name : TextView = itemView.findViewById(R.id.feed_comment2_name)
        val comment2Text : TextView = itemView.findViewById(R.id.feed_comment2_text)

        fun bind(item : FeedRecyclerViewData)
        {
            when
            {
                item.comment_count <= 0 -> {
                    comment1.visibility = View.GONE
                    comment2.visibility = View.GONE
                }
                item.comment_count == 1 -> {
                    comment1.visibility = View.VISIBLE
                    comment2.visibility = View.GONE
                    setOneCommentData(item)
                }
                item.comment_count > 1 -> {
                    comment1.visibility = View.VISIBLE
                    comment2.visibility = View.VISIBLE
                    setAllCommentData(item)
                }
            }

            nickName.text = item.owner.username
            time.text = item.created_at
            title.text = item.title
            question.text = item.text
            countHeart.text = item.like_count.toString()
            countMessage.text = item.like_count.toString()

            itemView.setOnClickListener {
                val intent = (Intent(itemView.context, CommentActivity::class.java))
                intent.putExtra("id", item.id)
                itemView.context.startActivity(intent)
            }
        }

        private fun setOneCommentData(item : FeedRecyclerViewData)
        {
            comment1Name.text = item.comment_preview[0].owner.username
            comment1Text.text = item.comment_preview[0].content
        }

        private fun setAllCommentData(item : FeedRecyclerViewData)
        {
            setOneCommentData(item)
            comment2Name.text = item.comment_preview[1].owner.username
            comment2Text.text = item.comment_preview[1].content
        }


    }
}