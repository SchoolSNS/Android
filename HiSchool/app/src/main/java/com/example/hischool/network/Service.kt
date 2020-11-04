package com.example.hischool.network

import com.example.hischool.data.FeedRecyclerViewData
import com.example.hischool.data.SearchFeedRecyclerViewData
import com.example.hischool.data.SearchSchoolRecyclerViewData
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

    @GET("/search/school")
    fun getSearchSchool(
        @Query("page") page: Int,
        @Query("query") query: String
    ) : Call<List<SearchSchoolRecyclerViewData>>

    @GET("/search/post")
    fun getSearchFeed(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ) : Call<List<SearchFeedRecyclerViewData>>
}