package com.example.hischool.view.activity

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.databinding.ActivityChattingBinding
import com.example.hischool.room.ChatDataBase
import com.example.hischool.room.DataBase
import com.example.hischool.viewModel.ChattingViewModel
import com.example.hischool.widget.toast
import com.example.sns.adapter.ChatAdapter
import kotlinx.android.synthetic.main.activity_chatting.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class ChattingActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityChattingBinding
    lateinit var viewModel: ChattingViewModel

    private lateinit var chatAdapter: ChatAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelProvider()
        init()
        observerViewModel()

    }

    private fun viewModelProvider()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chatting)
        viewModel = ViewModelProvider(this)[ChattingViewModel::class.java]
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
    }


    private fun init() {

        //Log.d("TAG", "name:"+viewModel.targetName)
        viewModel.connect()
        viewModel.chatDb = DataBase.getInstance(this)

        setRecyclerView()
        addData()
        Log.d("TAG", "name:" + viewModel.targetName)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun observerViewModel() {
        with(viewModel) {
            sendMessageBtn.observe(this@ChattingActivity, Observer {
                if (!TextUtils.isEmpty(messageEdit.value)) {
                    sendMessage()
                }
            })

            finishReceiveMessage.observe(this@ChattingActivity, Observer {

                chatAdapter.addItem(
                    ChatDataBase(
                        id = 0,
                        message = receiveMessage,
                        receiver = userName,
                        sender = sender,
                        time = receiveDate
                    )
                )

                insertReceiveData()

                chatAdapter.notifyDataSetChanged()
            })

            finishSend.observe(this@ChattingActivity, Observer {
                if (it) {
                    toast("전송성공")
                    chatAdapter.addItem(
                        ChatDataBase(
                            id = 0,
                            message = messageEdit.value.toString(),
                            receiver = viewModel.targetName,
                            sender = userName,
                            time = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
                        )
                    )

                    chatAdapter.notifyDataSetChanged()

                    insertSendData()

                    messageEdit.value = ""
                } else {
                    toast("전송 실패")
                }
            })
        }
    }

    private fun setRecyclerView() {
        chatAdapter = ChatAdapter(viewModel.arrayList)

        chat_recyclerview.adapter = chatAdapter

        chat_recyclerview.layoutManager = LinearLayoutManager(this)
        chat_recyclerview.setHasFixedSize(true)
    }

    private fun addData() {
        with(viewModel)
        {
            arrayList.clear()

            arrayList.addAll(
                chatDb?.dao()?.getMessage(
                    sender = targetName,
                    receiver = userName
                ) as ArrayList<ChatDataBase>
            )
        }

        chatAdapter.notifyDataSetChanged()
    }
}




