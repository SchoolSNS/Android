package com.example.hischool.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDao {

    @Query("SELECT * FROM login")
    fun getAll(): List<LoginData>

    @Insert
    fun insert(loginData: LoginData)

    @Delete
    fun delete(loginData: LoginData)
}