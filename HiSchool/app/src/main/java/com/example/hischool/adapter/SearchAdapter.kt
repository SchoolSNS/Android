package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.SearchRecyclerViewData

class SearchAdapter (val searchList : ArrayList<SearchRecyclerViewData>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.profile.setImageResource(searchList.get(position).profile)
        holder.nickname.text = searchList.get(position).nickname
        holder.time.text = searchList.get(position).time
        holder.title.text = searchList.get(position).title
        holder.question.text = searchList.get(position).question
        holder.count_heart.text = searchList.get(position).count_heart.toString()
        holder.count_message.text = searchList.get(position).count_message.toString()
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


