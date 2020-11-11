package com.example.hischool.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class ChatDataBase(
    @PrimaryKey(autoGenerate = true) var id: Long,
    @ColumnInfo(name = "sender") var sender: String,
    @ColumnInfo(name = "receiver") var receiver: String,
    @ColumnInfo(name = "message") var message: String,
    @ColumnInfo(name = "time") var time: Long
)