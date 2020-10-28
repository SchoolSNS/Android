package com.example.hischool.network.retrofit

import com.example.hischool.data.comment.CommentRecyclerViewData
import com.example.hischool.data.comment.WriteCommentResponse
import com.example.hischool.data.feed.FeedRecyclerViewData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Service {
    @GET("/feed/post/all")
    fun getFeed(
        @Header("Authorization") token: String,
        @Query("page") page : Int
    ) : Call<List<FeedRecyclerViewData>>

    @GET("/feed/post/post_id/comments")
    fun getComment(
        @Header("Authorization") token: String
    ) : Call<List<CommentRecyclerViewData>>

    @GET("/feed/post/post_id/comment")
    fun writeComment(
        @Header("Authorization") token: String,
        @Body WriteCommentBody: RequestBody
    ) : Call<WriteCommentResponse>
}