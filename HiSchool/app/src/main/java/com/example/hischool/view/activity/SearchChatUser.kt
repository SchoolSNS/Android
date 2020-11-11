package com.example.hischool.view.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.adapter.RoomListAdapter
import com.example.hischool.adapter.UserListAdapter
import com.example.hischool.data.UserListData
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.databinding.ActivityChattingRoomBinding
import com.example.hischool.databinding.ActivitySearchChatUserBinding
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.ChatDataBase
import com.example.hischool.room.DataBase
import com.example.hischool.sharedpreferences.App
import com.example.hischool.viewModel.ChattingViewModel
import com.example.hischool.widget.noFinishStartActivity
import com.example.hischool.widget.toast
import kotlinx.android.synthetic.main.activity_chatting_room.*
import kotlinx.android.synthetic.main.activity_chatting_room.chat_room_recyclerview
import kotlinx.android.synthetic.main.activity_search_chat_user.*
import kotlinx.android.synthetic.main.fragment_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.time.ZoneOffset

class SearchChatUser : AppCompatActivity() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit

    var userList : ArrayList<UserListData> = arrayListOf()
    lateinit var userListAdapter: UserListAdapter

    lateinit var binding: ActivitySearchChatUserBinding
    lateinit var viewModel: ChattingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelProvider()
        observerViewModel()
        init()
    }

    private fun init(){
        retrofit = RetrofitClient.getInstance()

        search_chat_btn.setOnClickListener {
            getUserList()
        }

    }
    private fun viewModelProvider()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_chat_user)
        viewModel = ViewModelProvider(this)[ChattingViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }


    override fun onResume() {
        super.onResume()
        viewModel.socketReset()
    }

    private fun observerViewModel() {
        with(viewModel) {
            finishUserConnect.observe(this@SearchChatUser, {
                Log.d("TAG", it.toString())
                if(!ChattingViewModel.without)
                    startActivity(Intent(this@SearchChatUser, ChattingActivity::class.java))

            })

            finishReceiveMessage.observe(this@SearchChatUser, {
                insertReceiveData()
            })
        }
    }


    private fun getUserList() {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getUserList(1, search_chat_edit.text.toString()).enqueue(object : Callback<List<UserListData>>{
            override fun onResponse(
                call: Call<List<UserListData>>,
                response: Response<List<UserListData>>
            ) {
                if(response.code() == 200)
                {
                    userList.clear()
                    userList = response.body() as ArrayList<UserListData>
                    Log.d("TAG", "data : $userList")
                    Log.d("TAG", "edit : ${search_chat_edit.text.toString()}")
                    userListAdapter = UserListAdapter(userList, applicationContext){
                        ChattingViewModel.without = false
                        viewModel.tryRoomConnect()
                        viewModel.targetProfile = it.profile
                    }
                    search_chat_recyclerview.setHasFixedSize(true)
                    search_chat_recyclerview.adapter = userListAdapter
                }
            }

            override fun onFailure(call: Call<List<UserListData>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}