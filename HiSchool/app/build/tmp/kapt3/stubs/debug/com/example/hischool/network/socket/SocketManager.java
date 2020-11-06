package com.example.hischool.network.socket;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0005R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/hischool/network/socket/SocketManager;", "", "()V", "observers", "Ljava/util/ArrayList;", "Lcom/example/hischool/network/socket/SocketListeners;", "Lkotlin/collections/ArrayList;", "socket", "Lcom/github/nkzawa/socketio/client/Socket;", "closeSocket", "", "connectSocket", "getSocket", "observe", "listener", "app_debug"})
public final class SocketManager {
    private static com.github.nkzawa.socketio.client.Socket socket;
    private static java.util.ArrayList<com.example.hischool.network.socket.SocketListeners> observers;
    public static final com.example.hischool.network.socket.SocketManager INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.github.nkzawa.socketio.client.Socket getSocket() {
        return null;
    }
    
    public final void observe(@org.jetbrains.annotations.NotNull()
    com.example.hischool.network.socket.SocketListeners listener) {
    }
    
    public final void closeSocket() {
    }
    
    public final void connectSocket() {
    }
    
    private SocketManager() {
        super();
    }
}