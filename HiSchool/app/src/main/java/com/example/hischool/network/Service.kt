package com.example.hischool.network

import com.example.hischool.data.FeedRecyclerViewData
import com.example.hischool.data.SignInResponse
import com.example.hischool.data.SignUpResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("/feed/post/all")
    fun getFeed(
        @Header("Authorization") token: String,
        @Query("page") page : Int
    ) : Call<List<FeedRecyclerViewData>>

    //업로드 부분
    @POST("/auth/sign-up")
    fun signUp(
        @Body signUpBody: RequestBody
    ): Call<SignUpResponse>

    //업로드 부분
    @POST("/auth/login")
    fun signIn(
        @Body signUpBody: RequestBody
    ): Call<SignInResponse>

}