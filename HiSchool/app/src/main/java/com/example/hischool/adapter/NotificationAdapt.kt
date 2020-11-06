package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hischool.R
import com.example.hischool.data.NotificationRecyclerViewData

class NotificationAdapt (val notificationList: ArrayList<NotificationRecyclerViewData>) : RecyclerView.Adapter<NotificationAdapt.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(notificationList[position])
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contents = itemView.findViewById<TextView>(R.id.textView)
        fun bind(item : NotificationRecyclerViewData)
        {
            contents.text = item.contents
        }
    }

}