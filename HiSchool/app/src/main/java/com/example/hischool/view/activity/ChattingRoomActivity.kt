package com.example.hischool.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.adapter.RoomListAdapter
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.databinding.ActivityChattingRoomBinding
import com.example.hischool.room.ChatDataBase
import com.example.hischool.room.DataBase
import com.example.hischool.sharedpreferences.App
import com.example.hischool.viewModel.ChattingViewModel
import com.example.hischool.widget.noFinishStartActivity
import com.example.hischool.widget.toast
import kotlinx.android.synthetic.main.activity_chatting_room.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class ChattingRoomActivity : AppCompatActivity() {

    lateinit var roomAdapter: RoomListAdapter

    lateinit var binding: ActivityChattingRoomBinding
    lateinit var viewModel: ChattingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelProvider()
        init()
        observerViewModel()

    }
    private fun init() {

        Glide.with(applicationContext)
            .load(LoginInformation.loginInfoData.profile)
            .transform(CenterCrop(), RoundedCorners(1000000000))
            .into(chatting_room_profile)

        Log.d("TAG", "image : ${LoginInformation.loginInfoData.profile}")

        Log.d("TAG" ,"image ${LoginInformation.loginInfoData.profile}")

        viewModel.chatDb = DataBase.getInstance(applicationContext)

        setRecyclerView()

        chatting_room_search.setOnClickListener {
            startActivity(Intent(this, SearchChatUser::class.java))
        }

    }

    private fun viewModelProvider()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_room)
        viewModel = ViewModelProvider(this)[ChattingViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }


    override fun onResume() {
        super.onResume()
        viewModel.socketReset()
        viewModel.setFragmentRecyclerViewData()
        setAdapter()
        Log.d("TAG", "roomResume")
    }

    private fun observerViewModel() {
        with(viewModel) {
            finishUserConnect.observe(this@ChattingRoomActivity, {
                Log.d("TAG", it.toString())
                if(!ChattingViewModel.without) startActivity(Intent(this@ChattingRoomActivity, ChattingActivity::class.java))
            })

            finishReceiveMessage.observe(this@ChattingRoomActivity, {

                insertReceiveData()
                setFragmentRecyclerViewData()
                setAdapter()
            })
        }
    }

    private fun setRecyclerView() {
        chat_room_recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        chat_room_recyclerview.setHasFixedSize(true)
    }


    private fun setAdapter() {
        viewModel.arrayList.forEach {
            Log.d("TAG", it.message)
        }
        roomAdapter = RoomListAdapter(viewModel.arrayList) {
            ChattingViewModel.without = false
            viewModel.tryRoomConnect()
        }

        chat_room_recyclerview.adapter = roomAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        App.prefs.setLastTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        super.onDestroy()
    }
}
