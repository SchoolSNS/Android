package com.example.hischool.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface LoginDao {

    @Query("SELECT * FROM login")
    fun getAll(): List<LoginData>

    @Insert
    fun insert(loginData: LoginData)

    @Query("DELETE FROM login")
    fun delete()
}