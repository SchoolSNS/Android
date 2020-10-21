package com.example.hischool.network

import com.example.hischool.data.FeedRecyclerViewData
import com.example.hischool.data.PostResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @POST("/feed/post")
    fun requestPost(
        @Header("Authorization") token: String,
        @Body postBody: RequestBody
    ): Call<PostResponse>
    @GET("/feed/post/all")
    fun getFeed(
        @Header("Authorization") token: String,
        @Query("page") page : Int
    ) : Call<List<FeedRecyclerViewData>>
}