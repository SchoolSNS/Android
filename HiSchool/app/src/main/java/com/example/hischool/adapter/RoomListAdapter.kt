package com.example.hischool.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.data.UserListData
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.ChatDataBase
import com.example.hischool.sharedpreferences.App
import kotlinx.coroutines.processNextEventInCurrentThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RoomListAdapter(
    var chatUserList: ArrayList<ChatDataBase>,
    private val handler: () -> Unit
) : RecyclerView.Adapter<RoomListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_room_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chatUserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(chatUserList[position], handler)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var myAPI: Service
        lateinit var retrofit: Retrofit
        lateinit var receiver: String
        val userName = itemView.findViewById<TextView>(R.id.chat_room_name)
        val lastMessage = itemView.findViewById<TextView>(R.id.chat_room_message)
        val profile: ImageView = itemView.findViewById(R.id.chat_room_image)
        fun bind(item: ChatDataBase, handler: () -> Unit) {
            receiver = item.receiver
            retrofit = RetrofitClient.getInstance()

            lastMessage.text = item.message

            getUserData()

            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "눌림", Toast.LENGTH_SHORT).show()
                App.prefs.setEmail(item.receiver)
                handler()
            }
        }

        private fun getUserData()
        {
            myAPI = retrofit.create(Service::class.java)
            myAPI.getUserProfile(receiver).enqueue(object : Callback<UserListData>{
                override fun onResponse(
                    call: Call<UserListData>,
                    response: Response<UserListData>
                ) {
                    Glide.with(itemView)
                        .load(response.body()?.profile)
                        .transform(CenterCrop(), RoundedCorners(10000))
                        .into(profile)

                    userName.text = response.body()?.username
                }

                override fun onFailure(call: Call<UserListData>, t: Throwable) {

                }

            })
        }
    }



}