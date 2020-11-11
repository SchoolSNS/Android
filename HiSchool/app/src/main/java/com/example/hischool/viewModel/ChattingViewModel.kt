package com.example.hischool.viewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hischool.data.ChatModel
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.network.socket.SocketListeners
import com.example.hischool.network.socket.SocketManager
import com.example.hischool.room.ChatDataBase
import com.example.hischool.room.DataBase
import com.example.hischool.sharedpreferences.App
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
    var targetProfile : String = ""

    val targetName : String by lazy { App.prefs.getEmail("") }
    val userName: String by lazy { LoginInformation.loginInfoData.email }

    var finishSend = MutableLiveData<Boolean>()
    var finishUserConnect = MutableLiveData<Boolean>()
    var finishReceiveMessage = MutableLiveData<Boolean>()

    var sendMessageBtn = SingleLiveEvent<Unit>()

    var mSocket: Socket? = null

    var arrayList = arrayListOf<ChatDataBase>()

    var chatDb: DataBase? = null
    companion object{
        var without : Boolean = false;
    }
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

    fun tryRoomConnect() {
        Log.d("TAG", "tryRoomConnect")
        val jsonObject = JSONObject()
        try {
            jsonObject.put("id", userName)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        mSocket?.emit("user connect", jsonObject)
    }

    override fun onMessageReceive(model: ChatModel) {
        Log.d("TAG", "REC")
        sender = model.name
        receiveMessage = model.message
        receiveDate = model.date
        finishReceiveMessage.value = true
        Log.d("TAG" , "ChattingReceiver")
    }

    override fun onConnect() {
        Log.d("TAG", "Connect!")
        without = true;

        tryRoomConnect()
    }

    override fun onDisconnect() {
        Log.d("TAG", "Disconnect!")
    }

    override fun onUserConnect(success: Boolean) {
        Log.d("TAG", "USERCONNECT WITH $without")
        if(!without) finishUserConnect.value = success
    }

    override fun onUserSendMessage(success: Boolean) {
        Log.d("TAG", "END")
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
        chatDb!!.dao().getRecentMessage(userName).forEach { Log.d("TAG", it.message) }
        Log.d("TAG", "user name is $userName")
        arrayList.addAll(
            chatDb!!.dao().getRecentMessage(userName).
                distinctBy {
                    it.receiver
                }.toList()
        )

    }
}
