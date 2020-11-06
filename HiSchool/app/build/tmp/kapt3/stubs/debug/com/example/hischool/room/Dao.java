package com.example.hischool.room;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\'J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\u0006H\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\'\u00a8\u0006\r"}, d2 = {"Lcom/example/hischool/room/Dao;", "", "getMessage", "", "Lcom/example/hischool/room/ChatDataBase;", "sender", "", "receiver", "getRecentMessage", "myName", "insert", "", "chatDataBase", "app_debug"})
public abstract interface Dao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM chat where (sender = :sender and receiver = :receiver) or (sender = :receiver and receiver = :sender)")
    public abstract java.util.List<com.example.hischool.room.ChatDataBase> getMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String sender, @org.jetbrains.annotations.NotNull()
    java.lang.String receiver);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from ( SELECT id, message, case WHEN sender == :myName THEN sender ELSE receiver END as sender, case WHEN sender == :myName THEN receiver ELSE sender END as receiver, time from chat where sender == :myName or receiver == :myName order by id DESC)tab group by receiver, sender")
    public abstract java.util.List<com.example.hischool.room.ChatDataBase> getRecentMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String myName);
    
    @androidx.room.Insert()
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.example.hischool.room.ChatDataBase chatDataBase);
}