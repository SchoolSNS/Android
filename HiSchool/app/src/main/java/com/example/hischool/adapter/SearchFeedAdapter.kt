package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.data.SearchFeedRecyclerViewData
import com.example.hischool.data.SearchSchoolRecyclerViewData

class SearchFeedAdapter(val searchFeedList: ArrayList<SearchFeedRecyclerViewData>) : RecyclerView.Adapter<SearchFeedAdapter.ViewHolder>() {

    fun updateList(list : ArrayList<SearchFeedRecyclerViewData>) {
        searchFeedList.clear()
        searchFeedList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchFeedList.size
    }

    override fun onBindViewHolder(holder: SearchFeedAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(searchFeedList[position].owner.image)
            .transform(CenterCrop(), RoundedCorners(25))
            .into(holder.profile)
        holder.username.text = searchFeedList[position].owner.username
        holder.time.text = searchFeedList[position].created_at
        holder.title.text = searchFeedList[position].title
        holder.content.text = searchFeedList[position].text
        holder.count_heart.text = searchFeedList[position].like_count.toString()
        holder.count_message.text = searchFeedList[position].comment_count.toString()
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val profile = itemView.findViewById<ImageView>(R.id.search_profile_image)
        val username = itemView.findViewById<TextView>(R.id.search_name_text)
        val time = itemView.findViewById<TextView>(R.id.search_time_text)
        val title = itemView.findViewById<TextView>(R.id.search_title_text)
        val content = itemView.findViewById<TextView>(R.id.search_question_text)
        val liked = itemView.findViewById<TextView>(R.id.search_heart_btn)
        val count_heart = itemView.findViewById<TextView>(R.id.search_count_heart_text)
        val count_message = itemView.findViewById<TextView>(R.id.search_count_message_text)
    }
}


