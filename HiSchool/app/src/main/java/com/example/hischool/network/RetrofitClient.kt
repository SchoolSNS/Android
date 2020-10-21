package com.example.hischool.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance : Retrofit? = null

    fun getInstance() : Retrofit {
        if(instance == null){
            instance = Retrofit.Builder()
                .baseUrl("http://10.80.161.202:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}