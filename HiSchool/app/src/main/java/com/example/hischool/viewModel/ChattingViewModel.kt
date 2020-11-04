package com.example.hischool.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hischool.data.ChatModel
import com.example.hischool.network.socket.SocketListeners
import com.example.hischool.network.socket.SocketManager
import com.example.hischool.room.ChatDataBase
import com.example.hischool.room.DataBase
import com.example.hischool.widget.MyApplication
import com.example.hischool.widget.SingleLiveEvent
import com.github.nkzawa.socketio.client.Socket
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class ChattingViewModel : ViewModel(), SocketListeners {

    var messageEdit = MutableLiveData<String>()

    var sender: String = ""
    var receiveMessage: String = ""
    var receiveDate: Long = 0

    val userName: String by lazy { MyApplication.prefs.getUsername( "") }
    val targetName: String by lazy { MyApplication.prefs.getUsername("") }

    var finishSend = MutableLiveData<Boolean>()
    var finishUserConnect = MutableLiveData<Boolean>()
    var finishReceiveMessage = MutableLiveData<Boolean>()

    var sendMessageBtn = SingleLiveEvent<Unit>()

    var mSocket: Socket? = null

    var arrayList = arrayListOf<ChatDataBase>()

    var chatDb: DataBase? = null

    fun connect() {
        Log.d("TAG", "connect")
        mSocket = SocketManager.getSocket()
        SocketManager.connectSocket()
        SocketManager.observe(this)
    }

    fun sendMessageBtnClick() {
        sendMessageBtn.call()
    }

    fun sendMessage() {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("room", targetName)
            jsonObject.put("message", messageEdit.value.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket?.emit("message", jsonObject)
    }

    fun tryRoomConnect(item: ChatDataBase) {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", userName)
            MyApplication.prefs.setUsername(item.receiver)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket?.emit("user connect", jsonObject)
    }

    override fun onMessageReceive(model: ChatModel) {

        sender = model.name
        sender = sender.substring(0, sender.length - 8)
        receiveMessage = model.message
        receiveDate = model.date
        finishReceiveMessage.value = true

        chatDb?.dao()?.insert(
            ChatDataBase(
                id = 0,
                message = receiveMessage,
                receiver = userName,
                sender = sender,
                time = receiveDate
            )
        )
    }

    override fun onConnect() {
        Log.d("TAG", "Connect!")
    }

    override fun onDisconnect() {
        Log.d("TAG", "Disconnect!")
    }

    override fun onUserConnect(success: Boolean) {
        finishUserConnect.value = success
    }

    override fun onUserSendMessage(success: Boolean) {
        finishSend.value = success
    }

    fun insertReceiveData() {
        chatDb?.dao()?.insert(
            ChatDataBase(
                id = 0,
                message = receiveMessage,
                receiver = userName,
                sender = sender,
                time = receiveDate
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertSendData() {
        chatDb?.dao()?.insert(
            ChatDataBase(
                id = 0,
                message = messageEdit.value.toString().trim(),
                receiver = targetName,
                sender = userName,
                time = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            )
        )
    }

    fun socketReset() {
        SocketManager.closeSocket()
        connect()
    }

    fun setFragmentRecyclerViewData() {
        arrayList.clear()

        arrayList.addAll(
            chatDb?.dao()?.getRecentMessage(userName)!! as ArrayList<ChatDataBase>
        )

    }
}
