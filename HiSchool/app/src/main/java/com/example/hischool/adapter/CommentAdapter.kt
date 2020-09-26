package com.example.hischool.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.CommentRecyclerViewData
import com.example.hischool.view.activity.CommentActivity
import kotlinx.android.synthetic.main.comment_item.view.*
import org.w3c.dom.Text

class CommentAdapter(val commentArrayList: ArrayList<CommentRecyclerViewData>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentArrayList[position])
    }

    override fun getItemCount(): Int {
        return commentArrayList.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val name  = itemView.findViewById<TextView>(R.id.comment_name)
        val message = itemView.findViewById<TextView>(R.id.comment_message)
        fun bind(item : CommentRecyclerViewData)
        {
            name.text = item.messsage
            message.text = item.messsage
        }
    }
}