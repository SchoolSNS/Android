package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import android.util.Log
import com.example.hischool.data.comment.CommentRecyclerViewData

class CommentAdapter(val commentArrayList: ArrayList<CommentRecyclerViewData>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentArrayList[position])
    }

    override fun getItemCount(): Int {
        Log.d("TAG", commentArrayList.size.toString())
        return commentArrayList.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val name  = itemView.findViewById<TextView>(R.id.comment_name)
        val message = itemView.findViewById<TextView>(R.id.comment_message)
        fun bind(item : CommentRecyclerViewData)
        {
            Log.d("TAG", item.owner.username)
            name.text = item.owner.username
            message.text = item.content
        }
    }
}