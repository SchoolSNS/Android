package com.example.hischool.network.retrofit

import com.example.hischool.data.*
import com.example.hischool.data.comment.CommentRecyclerViewData
import com.example.hischool.data.comment.CommentUpdateResponse
import com.example.hischool.data.comment.WriteCommentResponse
import com.example.hischool.data.feed.CheckLike
import com.example.hischool.data.feed.DelPostResponse
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.feed.FeedRecyclerViewDataOwner
import com.example.hischool.data.login.LoginInformation
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("/feed/post/all")
    fun getFeed(
        @Header("Authorization") token: String,
        @Query("page") page : Int
    ) : Call<List<FeedRecyclerViewData>>

    @GET("/feed/post/school")
    fun getSchoolFeed(
        @Header("Authorization") token: String,
        @Query("page") page : Int,
        @Query("school") school: String
    ) : Call<List<FeedRecyclerViewData>>

    @GET("/feed/post/{post_id}/comments")
    fun getComment(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int
    ) : Call<List<CommentRecyclerViewData>>

    @POST("/feed/post/{post_id}/comment")
    fun writeComment(
        @Header("Authorization") token: String,
        @Body WriteCommentBody: RequestBody,
        @Path ("post_id")postId : Int
    ) : Call<WriteCommentResponse>

    @DELETE("/feed/post/{post_id}")
    fun delPost(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int
    ) : Call<DelPostResponse>

    @DELETE("/feed/post/{post_id}/like")
    fun cancelLike(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int
    ) : Call<CheckLike>

    @POST("/feed/post/{post_id}/like")
    fun setLike(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int
    ) : Call<CheckLike>

    @DELETE("/feed/post/{post_id}/comment/{comment_id}")
    fun delComment(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int,
        @Path ("comment_id")commentId : Int
    ) : Call<CommentUpdateResponse>

    @PUT("/feed/post/{post_id}/comment/{comment_id}")
    fun updateComment(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int,
        @Path ("comment_id")commentId : Int,
        @Body UpdateCommentBody: RequestBody
    ) : Call<CommentUpdateResponse>

    @PUT("/feed/post/{post_id}")
    fun updateFeed(
        @Header("Authorization") token: String,
        @Path ("post_id")postId : Int,
        @Body UpdateCommentBody: RequestBody
    ) : Call<DelPostResponse>

    @GET("/search/school")
    fun getSearchSchool(
        @Query("page") page: Int,
        @Query("search") search: String
    ) : Call<List<SearchSchoolRecyclerViewData>>

    @GET("/search/post")
    fun getSearchFeed(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ) : Call<List<SearchFeedRecyclerViewData>>

    @POST("/feed/post")
    fun requestPost(
        @Header("Authorization") token: String,
        @Body postBody: RequestBody
    ): Call<PostResponse>

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

    @GET("/user")
    fun getUser(
        @Header("Authorization") token: String
    ): Call<LoginInformation>

    @GET("/search/search-user")
    fun getUserList(
        @Query("page") page: Int,
        @Query("search") query: String
    ) : Call<List<UserListData>>

    @GET("/user/email")
    fun getUserProfile(
        @Query("email") email: String
    ) : Call<UserListData>

}