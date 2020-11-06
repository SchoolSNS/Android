package com.example.hischool.network.socket;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lcom/example/hischool/network/socket/SocketListeners;", "", "onConnect", "", "onDisconnect", "onMessageReceive", "model", "Lcom/example/hischool/data/ChatModel;", "onUserConnect", "success", "", "onUserSendMessage", "app_debug"})
public abstract interface SocketListeners {
    
    public abstract void onMessageReceive(@org.jetbrains.annotations.NotNull()
    com.example.hischool.data.ChatModel model);
    
    public abstract void onConnect();
    
    public abstract void onDisconnect();
    
    public abstract void onUserConnect(boolean success);
    
    public abstract void onUserSendMessage(boolean success);
}