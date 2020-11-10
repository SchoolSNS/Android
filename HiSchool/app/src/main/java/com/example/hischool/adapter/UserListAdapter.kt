package com.example.hischool.adapter

import android.content.Context
import android.util.Log
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
import com.example.hischool.data.UserListData
import com.example.hischool.sharedpreferences.App

class UserListAdapter(
    var userList: ArrayList<UserListData>,
    val context: Context,
    private val handler: (UserListData) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_chat_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("TAG", "size : ${userList.size}")
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TAG", "holder$position ")
        holder.bind(userList[position], handler)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val targetProfile = itemView.findViewById<ImageView>(R.id.search_chat_profile)
        val targetName = itemView.findViewById<TextView>(R.id.search_chat_targetname)

        fun bind(item: UserListData, handler: (UserListData) -> Unit) {
            Log.d("TAG", "username ${item.username}")
            targetName.text = item.username
            Glide.with(context)
                .load(item.profile)
                .transform(CenterCrop(), RoundedCorners(100000000))
                .into(targetProfile)

            itemView.setOnClickListener {
                Log.d("TAG", "눌림")
                App.prefs.setEmail(item.email)
                handler(item)
            }
        }
    }

}