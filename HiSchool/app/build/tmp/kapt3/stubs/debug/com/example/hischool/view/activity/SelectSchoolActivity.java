package com.example.hischool.view.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001dJ\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R*\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006!"}, d2 = {"Lcom/example/hischool/view/activity/SelectSchoolActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mAdapter", "Lcom/example/hischool/adapter/SelectSchoolAdapter;", "getMAdapter", "()Lcom/example/hischool/adapter/SelectSchoolAdapter;", "setMAdapter", "(Lcom/example/hischool/adapter/SelectSchoolAdapter;)V", "myAPI", "Lcom/example/hischool/network/retrofit/Service;", "getMyAPI", "()Lcom/example/hischool/network/retrofit/Service;", "setMyAPI", "(Lcom/example/hischool/network/retrofit/Service;)V", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "setRetrofit", "(Lretrofit2/Retrofit;)V", "schoolList", "Ljava/util/ArrayList;", "Lcom/example/hischool/data/SearchSchoolRecyclerViewData;", "Lkotlin/collections/ArrayList;", "getSchoolList", "()Ljava/util/ArrayList;", "setSchoolList", "(Ljava/util/ArrayList;)V", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class SelectSchoolActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public com.example.hischool.network.retrofit.Service myAPI;
    @org.jetbrains.annotations.NotNull()
    public retrofit2.Retrofit retrofit;
    @org.jetbrains.annotations.NotNull()
    public com.example.hischool.adapter.SelectSchoolAdapter mAdapter;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.example.hischool.data.SearchSchoolRecyclerViewData> schoolList;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hischool.network.retrofit.Service getMyAPI() {
        return null;
    }
    
    public final void setMyAPI(@org.jetbrains.annotations.NotNull()
    com.example.hischool.network.retrofit.Service p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    public final void setRetrofit(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hischool.adapter.SelectSchoolAdapter getMAdapter() {
        return null;
    }
    
    public final void setMAdapter(@org.jetbrains.annotations.NotNull()
    com.example.hischool.adapter.SelectSchoolAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.example.hischool.data.SearchSchoolRecyclerViewData> getSchoolList() {
        return null;
    }
    
    public final void setSchoolList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.hischool.data.SearchSchoolRecyclerViewData> p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void getSchoolList() {
    }
    
    public SelectSchoolActivity() {
        super();
    }
}