package com.example.hischool.viewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0006\u0010D\u001a\u000205J\u0006\u0010E\u001a\u000205J\b\u0010F\u001a\u000205H\u0007J\b\u0010G\u001a\u000205H\u0016J\b\u0010H\u001a\u000205H\u0016J\u0010\u0010I\u001a\u0002052\u0006\u0010J\u001a\u00020KH\u0016J\u0010\u0010L\u001a\u0002052\u0006\u0010M\u001a\u00020\u0013H\u0016J\u0010\u0010N\u001a\u0002052\u0006\u0010M\u001a\u00020\u0013H\u0016J\u0006\u0010O\u001a\u000205J\u0006\u0010P\u001a\u000205J\u0006\u0010Q\u001a\u000205J\u0006\u0010R\u001a\u000205J\u000e\u0010S\u001a\u0002052\u0006\u0010T\u001a\u00020\u0006R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0015\"\u0004\b\'\u0010\u0017R\u001a\u0010(\u001a\u00020)X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020%X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R \u00103\u001a\b\u0012\u0004\u0012\u00020504X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010:\u001a\u00020%X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b;\u00100\"\u0004\b<\u00102R\u001b\u0010=\u001a\u00020%8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b?\u0010@\u001a\u0004\b>\u00100R\u001b\u0010A\u001a\u00020%8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bC\u0010@\u001a\u0004\bB\u00100\u00a8\u0006U"}, d2 = {"Lcom/example/hischool/viewModel/ChattingViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/example/hischool/network/socket/SocketListeners;", "()V", "arrayList", "Ljava/util/ArrayList;", "Lcom/example/hischool/room/ChatDataBase;", "getArrayList", "()Ljava/util/ArrayList;", "setArrayList", "(Ljava/util/ArrayList;)V", "chatDb", "Lcom/example/hischool/room/DataBase;", "getChatDb", "()Lcom/example/hischool/room/DataBase;", "setChatDb", "(Lcom/example/hischool/room/DataBase;)V", "finishReceiveMessage", "Landroidx/lifecycle/MutableLiveData;", "", "getFinishReceiveMessage", "()Landroidx/lifecycle/MutableLiveData;", "setFinishReceiveMessage", "(Landroidx/lifecycle/MutableLiveData;)V", "finishSend", "getFinishSend", "setFinishSend", "finishUserConnect", "getFinishUserConnect", "setFinishUserConnect", "mSocket", "Lcom/github/nkzawa/socketio/client/Socket;", "getMSocket", "()Lcom/github/nkzawa/socketio/client/Socket;", "setMSocket", "(Lcom/github/nkzawa/socketio/client/Socket;)V", "messageEdit", "", "getMessageEdit", "setMessageEdit", "receiveDate", "", "getReceiveDate", "()J", "setReceiveDate", "(J)V", "receiveMessage", "getReceiveMessage", "()Ljava/lang/String;", "setReceiveMessage", "(Ljava/lang/String;)V", "sendMessageBtn", "Lcom/example/hischool/widget/SingleLiveEvent;", "", "getSendMessageBtn", "()Lcom/example/hischool/widget/SingleLiveEvent;", "setSendMessageBtn", "(Lcom/example/hischool/widget/SingleLiveEvent;)V", "sender", "getSender", "setSender", "targetName", "getTargetName", "targetName$delegate", "Lkotlin/Lazy;", "userName", "getUserName", "userName$delegate", "connect", "insertReceiveData", "insertSendData", "onConnect", "onDisconnect", "onMessageReceive", "model", "Lcom/example/hischool/data/ChatModel;", "onUserConnect", "success", "onUserSendMessage", "sendMessage", "sendMessageBtnClick", "setFragmentRecyclerViewData", "socketReset", "tryRoomConnect", "item", "app_debug"})
public final class ChattingViewModel extends androidx.lifecycle.ViewModel implements com.example.hischool.network.socket.SocketListeners {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> messageEdit;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sender = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String receiveMessage = "";
    private long receiveDate = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy userName$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy targetName$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> finishSend;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> finishUserConnect;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> finishReceiveMessage;
    @org.jetbrains.annotations.NotNull()
    private com.example.hischool.widget.SingleLiveEvent<kotlin.Unit> sendMessageBtn;
    @org.jetbrains.annotations.Nullable()
    private com.github.nkzawa.socketio.client.Socket mSocket;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.example.hischool.room.ChatDataBase> arrayList;
    @org.jetbrains.annotations.Nullable()
    private com.example.hischool.room.DataBase chatDb;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getMessageEdit() {
        return null;
    }
    
    public final void setMessageEdit(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSender() {
        return null;
    }
    
    public final void setSender(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReceiveMessage() {
        return null;
    }
    
    public final void setReceiveMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getReceiveDate() {
        return 0L;
    }
    
    public final void setReceiveDate(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTargetName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getFinishSend() {
        return null;
    }
    
    public final void setFinishSend(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getFinishUserConnect() {
        return null;
    }
    
    public final void setFinishUserConnect(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getFinishReceiveMessage() {
        return null;
    }
    
    public final void setFinishReceiveMessage(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hischool.widget.SingleLiveEvent<kotlin.Unit> getSendMessageBtn() {
        return null;
    }
    
    public final void setSendMessageBtn(@org.jetbrains.annotations.NotNull()
    com.example.hischool.widget.SingleLiveEvent<kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.github.nkzawa.socketio.client.Socket getMSocket() {
        return null;
    }
    
    public final void setMSocket(@org.jetbrains.annotations.Nullable()
    com.github.nkzawa.socketio.client.Socket p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.example.hischool.room.ChatDataBase> getArrayList() {
        return null;
    }
    
    public final void setArrayList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.hischool.room.ChatDataBase> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.hischool.room.DataBase getChatDb() {
        return null;
    }
    
    public final void setChatDb(@org.jetbrains.annotations.Nullable()
    com.example.hischool.room.DataBase p0) {
    }
    
    public final void connect() {
    }
    
    public final void sendMessageBtnClick() {
    }
    
    public final void sendMessage() {
    }
    
    public final void tryRoomConnect(@org.jetbrains.annotations.NotNull()
    com.example.hischool.room.ChatDataBase item) {
    }
    
    @java.lang.Override()
    public void onMessageReceive(@org.jetbrains.annotations.NotNull()
    com.example.hischool.data.ChatModel model) {
    }
    
    @java.lang.Override()
    public void onConnect() {
    }
    
    @java.lang.Override()
    public void onDisconnect() {
    }
    
    @java.lang.Override()
    public void onUserConnect(boolean success) {
    }
    
    @java.lang.Override()
    public void onUserSendMessage(boolean success) {
    }
    
    public final void insertReceiveData() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public final void insertSendData() {
    }
    
    public final void socketReset() {
    }
    
    public final void setFragmentRecyclerViewData() {
    }
    
    public ChattingViewModel() {
        super();
    }
}