package com.example.hischool.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
class LoginData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "token") var token: String
)