package com.example.hischool.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B%\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000bH\u0017J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/hischool/adapter/FeedAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/hischool/adapter/FeedAdapter$ViewHolder;", "feedList", "Ljava/util/ArrayList;", "Lcom/example/hischool/data/feed/FeedRecyclerViewData;", "Lkotlin/collections/ArrayList;", "context", "Landroid/content/Context;", "(Ljava/util/ArrayList;Landroid/content/Context;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
public final class FeedAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.hischool.adapter.FeedAdapter.ViewHolder> {
    private final java.util.ArrayList<com.example.hischool.data.feed.FeedRecyclerViewData> feedList = null;
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.hischool.adapter.FeedAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.N)
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.hischool.adapter.FeedAdapter.ViewHolder holder, int position) {
    }
    
    public FeedAdapter(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.hischool.data.feed.FeedRecyclerViewData> feedList, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0007J\u0010\u0010W\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010X\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010Y\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010Z\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010[\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010\\\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010]\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010^\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010_\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010`\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010a\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002J\u0010\u0010b\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0002R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR\u0011\u0010\u001b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000eR\u0011\u0010\u001d\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000eR\u0011\u0010\u001f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u000eR\u0011\u0010!\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0011\u0010#\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0019\u0010%\u001a\n &*\u0004\u0018\u00010\u00140\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0016R\u0019\u0010(\u001a\n &*\u0004\u0018\u00010\u00140\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0016R\u0011\u0010*\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u000eR\u0011\u0010,\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u000eR\u0011\u0010.\u001a\u00020/\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u00102\u001a\u000203\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u00106\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u00109\u001a\u00020:\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u001a\u0010=\u001a\u00020>X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0019\u0010C\u001a\n &*\u0004\u0018\u00010\u00140\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0016R\u0019\u0010E\u001a\n &*\u0004\u0018\u00010\f0\f\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010\u000eR\u0019\u0010G\u001a\n &*\u0004\u0018\u00010\u00140\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0016R\u001a\u0010I\u001a\u00020JX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u0019\u0010O\u001a\n &*\u0004\u0018\u00010\u00140\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0016R\u0019\u0010Q\u001a\n &*\u0004\u0018\u00010\u00140\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0016\u00a8\u0006c"}, d2 = {"Lcom/example/hischool/adapter/FeedAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Lcom/example/hischool/adapter/FeedAdapter;Landroid/view/View;Landroid/content/Context;)V", "comment1", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getComment1", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "comment1Image", "Landroid/widget/ImageView;", "getComment1Image", "()Landroid/widget/ImageView;", "comment1ImageList", "getComment1ImageList", "comment1ImageList2", "getComment1ImageList2", "comment1Name", "Landroid/widget/TextView;", "getComment1Name", "()Landroid/widget/TextView;", "comment1Text", "getComment1Text", "comment2", "getComment2", "comment2Image", "getComment2Image", "comment2ImageList", "getComment2ImageList", "comment2ImageList2", "getComment2ImageList2", "comment2Name", "getComment2Name", "comment2Text", "getComment2Text", "countHeart", "kotlin.jvm.PlatformType", "getCountHeart", "countMessage", "getCountMessage", "feedImage1", "getFeedImage1", "feedImage2", "getFeedImage2", "feedTime", "Lcom/example/hischool/module/FeedTime;", "getFeedTime", "()Lcom/example/hischool/module/FeedTime;", "heartBtn", "Landroid/widget/ImageButton;", "getHeartBtn", "()Landroid/widget/ImageButton;", "mContext", "getMContext", "()Landroid/content/Context;", "moreBtn", "Landroid/widget/Button;", "getMoreBtn", "()Landroid/widget/Button;", "myAPI", "Lcom/example/hischool/network/retrofit/Service;", "getMyAPI", "()Lcom/example/hischool/network/retrofit/Service;", "setMyAPI", "(Lcom/example/hischool/network/retrofit/Service;)V", "nickName", "getNickName", "profile", "getProfile", "question", "getQuestion", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "setRetrofit", "(Lretrofit2/Retrofit;)V", "time", "getTime", "title", "getTitle", "bind", "", "item", "Lcom/example/hischool/data/feed/FeedRecyclerViewData;", "cancelLike", "setAllCommentData", "setAllCommentPreviewImage", "setAllFeedImage", "setAllImageCommentOne", "setAllImageCommentTwo", "setLike", "setOneCommentData", "setOneCommentPreviewImage", "setOneFeedImage", "setOneImageCommentOne", "setOneImageCommentTwo", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        public com.example.hischool.network.retrofit.Service myAPI;
        @org.jetbrains.annotations.NotNull()
        public retrofit2.Retrofit retrofit;
        @org.jetbrains.annotations.NotNull()
        private final com.example.hischool.module.FeedTime feedTime = null;
        private final android.widget.ImageView profile = null;
        private final android.widget.TextView nickName = null;
        private final android.widget.TextView time = null;
        private final android.widget.TextView title = null;
        private final android.widget.TextView question = null;
        private final android.widget.TextView countHeart = null;
        private final android.widget.TextView countMessage = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.constraintlayout.widget.ConstraintLayout comment1 = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.constraintlayout.widget.ConstraintLayout comment2 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView comment1Image = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView comment1Name = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView comment1Text = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView comment2Image = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView comment2Name = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView comment2Text = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView comment1ImageList = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView comment1ImageList2 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView comment2ImageList = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView comment2ImageList2 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.content.Context mContext = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton heartBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.Button moreBtn = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView feedImage1 = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView feedImage2 = null;
        
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
        public final com.example.hischool.module.FeedTime getFeedTime() {
            return null;
        }
        
        public final android.widget.ImageView getProfile() {
            return null;
        }
        
        public final android.widget.TextView getNickName() {
            return null;
        }
        
        public final android.widget.TextView getTime() {
            return null;
        }
        
        public final android.widget.TextView getTitle() {
            return null;
        }
        
        public final android.widget.TextView getQuestion() {
            return null;
        }
        
        public final android.widget.TextView getCountHeart() {
            return null;
        }
        
        public final android.widget.TextView getCountMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.constraintlayout.widget.ConstraintLayout getComment1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.constraintlayout.widget.ConstraintLayout getComment2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getComment1Image() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getComment1Name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getComment1Text() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getComment2Image() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getComment2Name() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getComment2Text() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getComment1ImageList() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getComment1ImageList2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getComment2ImageList() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getComment2ImageList2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context getMContext() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getHeartBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.Button getMoreBtn() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getFeedImage1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getFeedImage2() {
            return null;
        }
        
        @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.N)
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setOneFeedImage(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setAllFeedImage(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setOneCommentData(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setAllCommentData(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setOneImageCommentOne(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setAllImageCommentOne(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setOneImageCommentTwo(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setAllImageCommentTwo(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setOneCommentPreviewImage(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setAllCommentPreviewImage(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void cancelLike(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        private final void setLike(com.example.hischool.data.feed.FeedRecyclerViewData item) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView, @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            super(null);
        }
    }
}