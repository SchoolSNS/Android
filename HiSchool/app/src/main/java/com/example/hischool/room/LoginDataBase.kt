package com.example.hischool.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoginData::class], version = 5)
abstract class LoginDataBase : RoomDatabase(){

    abstract fun loginDao(): LoginDao

    companion object{
        private var INSTANCE: LoginDataBase? = null

        fun getInstance(context: Context): LoginDataBase? {
            if(INSTANCE == null){
                synchronized(LoginData::class.java){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDataBase::class.java, "login.db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}