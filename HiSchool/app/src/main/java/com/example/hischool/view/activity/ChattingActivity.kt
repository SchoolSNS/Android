package com.example.hischool.view.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.RoomListAdapter
import com.example.hischool.databinding.FragmentChattingBinding
import com.example.hischool.room.ChatDataBase
import com.example.hischool.room.DataBase
import com.example.hischool.viewModel.ChattingViewModel
import com.example.hischool.widget.MyApplication
import com.example.hischool.widget.noFinishStartActivity
import com.example.hischool.widget.toast
import kotlinx.android.synthetic.main.fragment_chatting.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class ChattingActivity : AppCompatActivity() {

    lateinit var roomAdapter: RoomListAdapter
    lateinit var mContext: Context

    lateinit var binding: FragmentChattingBinding
    lateinit var viewModel: ChattingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelProvider()
        init()
        observerViewModel()

    }
    private fun init() {
        viewModel.chatDb = DataBase.getInstance(mContext)

        setRecyclerView()

        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.d("TAG", "버튼 클릭")
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun viewModelProvider()
    {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting)
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
    }

    private fun observerViewModel() {
        with(viewModel) {
            finishUserConnect.observe(this@ChattingActivity, {
                Log.d("TAG", it.toString())
                if (it) {
                    toast("입장")
                    noFinishStartActivity(ChattingRoomActivity::class.java)
                } else {
                    toast("실패")
                }
            })
        }
    }

    private fun setRecyclerView() {
        chat_room_recyclerview.layoutManager = LinearLayoutManager(mContext)
        chat_room_recyclerview.setHasFixedSize(true)
    }


    private fun setAdapter() {
        roomAdapter = RoomListAdapter(viewModel.arrayList) { item: ChatDataBase ->
            viewModel.tryRoomConnect(
                item
            )
        }

        chat_room_recyclerview.adapter = roomAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        MyApplication.prefs.setLastTime(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        super.onDestroy()
    }
}
