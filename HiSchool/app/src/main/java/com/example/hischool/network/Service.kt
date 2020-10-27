package com.example.hischool.network

import com.example.hischool.data.FeedRecyclerViewData
import com.example.hischool.data.SearchRecyclerViewData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Service {
    @GET("/feed/post/all")
    fun getFeed(
        @Header("Authorization") token: String,
        @Query("page") page : Int
    ) : Call<List<FeedRecyclerViewData>>

    @GET("/feed/post/all")
    fun getSearchFeed(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ) : Call<SearchRecyclerViewData>
}