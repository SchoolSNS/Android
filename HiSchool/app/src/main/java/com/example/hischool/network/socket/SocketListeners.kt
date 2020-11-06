package com.example.hischool.network.socket

import com.example.hischool.data.ChatModel

interface SocketListeners {
    fun onMessageReceive(model: ChatModel)
    fun onConnect()
    fun onDisconnect()
    fun onUserConnect(success: Boolean)
    fun onUserSendMessage(success: Boolean)
}