package com.example.hischool.view.activity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 <2\u00020\u0001:\u0001<B\u0005\u00a2\u0006\u0002\u0010\u0002J\"\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\n2\u0006\u00104\u001a\u00020\n2\b\u00105\u001a\u0004\u0018\u000106H\u0014J\u0012\u00107\u001a\u0002022\b\u00108\u001a\u0004\u0018\u000109H\u0014J\u0006\u0010:\u001a\u000202J\u0006\u0010;\u001a\u000202R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R!\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R!\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0012j\b\u0012\u0004\u0012\u00020\u0004`\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R*\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0012j\b\u0012\u0004\u0012\u00020\u0004`\u0014X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\f\"\u0004\b\'\u0010\u000eR\u001a\u0010(\u001a\u00020)X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\b\u00a8\u0006="}, d2 = {"Lcom/example/hischool/view/activity/EditFeedActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "content", "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "count", "", "getCount", "()I", "setCount", "(I)V", "editFeedSetImageAdapter", "Lcom/example/hischool/adapter/EditCommentSetImageAdapter;", "imageBitmap", "Ljava/util/ArrayList;", "Landroid/graphics/Bitmap;", "Lkotlin/collections/ArrayList;", "getImageBitmap", "()Ljava/util/ArrayList;", "imageMultipart", "Lokhttp3/RequestBody;", "imageNameList", "getImageNameList", "imageUrls", "getImageUrls", "setImageUrls", "(Ljava/util/ArrayList;)V", "myAPI", "Lcom/example/hischool/network/retrofit/Service;", "getMyAPI", "()Lcom/example/hischool/network/retrofit/Service;", "setMyAPI", "(Lcom/example/hischool/network/retrofit/Service;)V", "postId", "getPostId", "setPostId", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "setRetrofit", "(Lretrofit2/Retrofit;)V", "title", "getTitle", "setTitle", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "pickImageFromGallery", "updateFeed", "Companion", "app_debug"})
public final class EditFeedActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public retrofit2.Retrofit retrofit;
    @org.jetbrains.annotations.NotNull()
    public com.example.hischool.network.retrofit.Service myAPI;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String content;
    @org.jetbrains.annotations.NotNull()
    public java.lang.String title;
    @org.jetbrains.annotations.NotNull()
    public java.util.ArrayList<java.lang.String> imageUrls;
    private int postId = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<android.graphics.Bitmap> imageBitmap = null;
    private final java.util.ArrayList<okhttp3.RequestBody> imageMultipart = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<java.lang.String> imageNameList = null;
    private com.example.hischool.adapter.EditCommentSetImageAdapter editFeedSetImageAdapter;
    private int count = 1;
    private static final int IMAGE_PICK_CODE = 1000;
    public static final com.example.hischool.view.activity.EditFeedActivity.Companion Companion = null;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    public final void setRetrofit(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hischool.network.retrofit.Service getMyAPI() {
        return null;
    }
    
    public final void setMyAPI(@org.jetbrains.annotations.NotNull()
    com.example.hischool.network.retrofit.Service p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContent() {
        return null;
    }
    
    public final void setContent(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getImageUrls() {
        return null;
    }
    
    public final void setImageUrls(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.String> p0) {
    }
    
    public final int getPostId() {
        return 0;
    }
    
    public final void setPostId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<android.graphics.Bitmap> getImageBitmap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getImageNameList() {
        return null;
    }
    
    public final int getCount() {
        return 0;
    }
    
    public final void setCount(int p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public final void updateFeed() {
    }
    
    public final void pickImageFromGallery() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    public EditFeedActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/hischool/view/activity/EditFeedActivity$Companion;", "", "()V", "IMAGE_PICK_CODE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}