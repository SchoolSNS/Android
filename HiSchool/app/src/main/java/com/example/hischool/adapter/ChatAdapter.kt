package com.example.hischool.adapter

import android.content.Context
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
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.ChatDataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ChatAdapter(var arrayList: ArrayList<ChatDataBase>, var context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addItem(item: ChatDataBase) {//아이템 추가
        arrayList.add(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == 1) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_chat, parent, false)
            return Holder(view)
        }
        //getItemViewType 에서 뷰타입 2을 리턴받았다면 상대채팅레이아웃을 받은 Holder2를 리턴
        else {
            view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_your_chat, parent, false)
            return Holder2(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.chat_Text.text = arrayList[position].message
        }
        //onCreateViewHolder에서 리턴받은 뷰홀더가 Holder2라면 상대의 채팅, item_your_chat의 뷰들을 초기화 해줌
        else if (holder is Holder2) {
            holder.chat_Text.text = arrayList[position].message
            holder.getProfile(arrayList[position].sender)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //친구목록 모델의 변수들 정의하는부분
        val chat_Text = itemView.findViewById<TextView>(R.id.chat_Text)
    }

    //상대가친 채팅 뷰홀더
    inner class Holder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var myAPI: Service
        lateinit var retrofit: Retrofit
        val chat_Text = itemView.findViewById<TextView>(R.id.chat_Text)
        val chat_name = itemView.findViewById<TextView>(R.id.chat_You_Name)
        val chatImage:ImageView = itemView.findViewById(R.id.chat_You_Image)

        fun getProfile(receiver : String)
        {
            retrofit = RetrofitClient.getInstance()
            myAPI = retrofit.create(Service::class.java)
            myAPI.getUserProfile(receiver).enqueue(object : Callback<UserListData> {
                override fun onResponse(
                    call: Call<UserListData>,
                    response: Response<UserListData>
                ) {
                    Glide.with(context)
                        .load(response.body()?.profile)
                        .transform(CenterCrop(), RoundedCorners(10000))
                        .into(chatImage)
                    chat_name.text = response.body()?.username
                }

                override fun onFailure(call: Call<UserListData>, t: Throwable) {

                }

            })
        }
    }

    override fun getItemViewType(position: Int): Int {//여기서 뷰타입을 1, 2로 바꿔서 지정해줘야 내채팅 너채팅을 바꾸면서 쌓을 수 있음

        //내 아이디와 arraylist의 name이 같다면 내꺼 아니면 상대꺼
        return if (arrayList[position].sender == LoginInformation.loginInfoData.email) {
            1
        } else {
            2
        }
    }
}