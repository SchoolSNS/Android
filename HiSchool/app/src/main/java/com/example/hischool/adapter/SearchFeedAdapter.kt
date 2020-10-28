package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.SearchFeedRecyclerViewData

class SearchFeedAdapter(val searchFeedList: ArrayList<SearchFeedRecyclerViewData>) : RecyclerView.Adapter<SearchFeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchFeedList.size
    }

    override fun onBindViewHolder(holder: SearchFeedAdapter.ViewHolder, position: Int) {
        holder.profile.setImageResource(searchFeedList.get(position).id)
        holder.nickname.text = searchFeedList.get(position).owner.username
        holder.time.text = searchFeedList.get(position).created_at
        holder.title.text = searchFeedList.get(position).title
        holder.question.text = searchFeedList.get(position).text
        holder.count_heart.text = searchFeedList.get(position).like_count.toString()
        holder.count_message.text = searchFeedList.get(position).comment_count.toString()
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val profile = itemView.findViewById<ImageView>(R.id.search_profile_image)
        val nickname = itemView.findViewById<TextView>(R.id.search_name_text)
        val time = itemView.findViewById<TextView>(R.id.search_time_text)
        val title = itemView.findViewById<TextView>(R.id.search_title_text)
        val question = itemView.findViewById<TextView>(R.id.search_question_text)
        val count_heart = itemView.findViewById<TextView>(R.id.search_count_heart_text)
        val count_message = itemView.findViewById<TextView>(R.id.search_count_message_text)
    }
}


