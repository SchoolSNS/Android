package com.example.hischool.network.retrofit;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J,\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\bH\'J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J(\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J(\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\bH\'J2\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u000f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\b2\b\b\u0001\u0010\u0016\u001a\u00020\u0006H\'J(\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000f0\u00032\b\b\u0001\u0010\u0013\u001a\u00020\b2\b\b\u0001\u0010\u0016\u001a\u00020\u0006H\'J\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001d\u001a\u00020\u001eH\'J\"\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\"\u001a\u00020\u001eH\'J\u0018\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00032\b\b\u0001\u0010\"\u001a\u00020\u001eH\'J6\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\b2\b\b\u0001\u0010&\u001a\u00020\u001eH\'J,\u0010\'\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010&\u001a\u00020\u001eH\'J,\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020\u001e2\b\b\u0001\u0010\u0007\u001a\u00020\bH\'\u00a8\u0006+"}, d2 = {"Lcom/example/hischool/network/retrofit/Service;", "", "cancelLike", "Lretrofit2/Call;", "Lcom/example/hischool/data/feed/CheckLike;", "token", "", "postId", "", "delComment", "Lcom/example/hischool/data/comment/CommentUpdateResponse;", "commentId", "delPost", "Lcom/example/hischool/data/feed/DelPostResponse;", "getComment", "", "Lcom/example/hischool/data/comment/CommentRecyclerViewData;", "getFeed", "Lcom/example/hischool/data/feed/FeedRecyclerViewData;", "page", "getSearchFeed", "Lcom/example/hischool/data/SearchFeedRecyclerViewData;", "query", "getSearchSchool", "Lcom/example/hischool/data/SearchSchoolRecyclerViewData;", "getUser", "Lcom/example/hischool/data/login/LoginInformation;", "requestPost", "Lcom/example/hischool/data/PostResponse;", "postBody", "Lokhttp3/RequestBody;", "setLike", "signIn", "Lcom/example/hischool/data/SignInResponse;", "signUpBody", "signUp", "Lcom/example/hischool/data/SignUpResponse;", "updateComment", "UpdateCommentBody", "updateFeed", "writeComment", "Lcom/example/hischool/data/comment/WriteCommentResponse;", "WriteCommentBody", "app_debug"})
public abstract interface Service {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/feed/post/all")
    public abstract retrofit2.Call<java.util.List<com.example.hischool.data.feed.FeedRecyclerViewData>> getFeed(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Query(value = "page")
    int page);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/feed/post/{post_id}/comments")
    public abstract retrofit2.Call<java.util.List<com.example.hischool.data.comment.CommentRecyclerViewData>> getComment(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/feed/post/{post_id}/comment")
    public abstract retrofit2.Call<com.example.hischool.data.comment.WriteCommentResponse> writeComment(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody WriteCommentBody, @retrofit2.http.Path(value = "post_id")
    int postId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "/feed/post/{post_id}")
    public abstract retrofit2.Call<com.example.hischool.data.feed.DelPostResponse> delPost(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "/feed/post/{post_id}/like")
    public abstract retrofit2.Call<com.example.hischool.data.feed.CheckLike> cancelLike(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/feed/post/{post_id}/like")
    public abstract retrofit2.Call<com.example.hischool.data.feed.CheckLike> setLike(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "/feed/post/{post_id}/comment/{comment_id}")
    public abstract retrofit2.Call<com.example.hischool.data.comment.CommentUpdateResponse> delComment(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId, @retrofit2.http.Path(value = "comment_id")
    int commentId);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "/feed/post/{post_id}/comment/{comment_id}")
    public abstract retrofit2.Call<com.example.hischool.data.comment.CommentUpdateResponse> updateComment(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId, @retrofit2.http.Path(value = "comment_id")
    int commentId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody UpdateCommentBody);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "/feed/post/{post_id}")
    public abstract retrofit2.Call<com.example.hischool.data.feed.DelPostResponse> updateFeed(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Path(value = "post_id")
    int postId, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody UpdateCommentBody);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/search/school")
    public abstract retrofit2.Call<java.util.List<com.example.hischool.data.SearchSchoolRecyclerViewData>> getSearchSchool(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "query")
    java.lang.String query);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/search/post")
    public abstract retrofit2.Call<java.util.List<com.example.hischool.data.SearchFeedRecyclerViewData>> getSearchFeed(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "query")
    java.lang.String query);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/feed/post")
    public abstract retrofit2.Call<com.example.hischool.data.PostResponse> requestPost(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody postBody);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/auth/sign-up")
    public abstract retrofit2.Call<com.example.hischool.data.SignUpResponse> signUp(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody signUpBody);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/auth/login")
    public abstract retrofit2.Call<com.example.hischool.data.SignInResponse> signIn(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody signUpBody);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "/user")
    public abstract retrofit2.Call<com.example.hischool.data.login.LoginInformation> getUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String token);
}