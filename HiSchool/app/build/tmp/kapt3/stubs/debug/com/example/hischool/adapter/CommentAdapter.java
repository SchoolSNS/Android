package com.example.hischool.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001dB5\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\b\u0010\u0014\u001a\u00020\nH\u0016J\u001c\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0018\u001a\u00020\nH\u0016J\u001c\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\nH\u0016R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001e"}, d2 = {"Lcom/example/hischool/adapter/CommentAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/hischool/adapter/CommentAdapter$ViewHolder;", "commentArrayList", "Ljava/util/ArrayList;", "Lcom/example/hischool/data/comment/CommentRecyclerViewData;", "Lkotlin/collections/ArrayList;", "context", "Landroid/content/Context;", "postId", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Ljava/util/ArrayList;Landroid/content/Context;ILandroidx/fragment/app/FragmentManager;)V", "getCommentArrayList", "()Ljava/util/ArrayList;", "getFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "getPostId", "()I", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"})
public final class CommentAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.hischool.adapter.CommentAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.example.hischool.data.comment.CommentRecyclerViewData> commentArrayList = null;
    private final android.content.Context context = null;
    private final int postId = 0;
    @org.jetbrains.annotations.NotNull()
    private final androidx.fragment.app.FragmentManager fragmentManager = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.hischool.adapter.CommentAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.hischool.adapter.CommentAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.example.hischool.data.comment.CommentRecyclerViewData> getCommentArrayList() {
        return null;
    }
    
    public final int getPostId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.fragment.app.FragmentManager getFragmentManager() {
        return null;
    }
    
    public CommentAdapter(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.hischool.data.comment.CommentRecyclerViewData> commentArrayList, @org.jetbrains.annotations.NotNull()
    android.content.Context context, int postId, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fragmentManager) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u0010\u0010\'\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u001f\u001a\n \u001c*\u0004\u0018\u00010\u001b0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010!\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000e\u00a8\u0006)"}, d2 = {"Lcom/example/hischool/adapter/CommentAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "context", "Landroid/content/Context;", "postId", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "(Lcom/example/hischool/adapter/CommentAdapter;Landroid/view/View;Landroid/content/Context;ILandroidx/fragment/app/FragmentManager;)V", "imageList", "Landroid/widget/ImageView;", "getImageList", "()Landroid/widget/ImageView;", "imageList2", "getImageList2", "mContext", "getMContext", "()Landroid/content/Context;", "mFragmentManager", "getMFragmentManager", "()Landroidx/fragment/app/FragmentManager;", "mPostId", "getMPostId", "()I", "message", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getMessage", "()Landroid/widget/TextView;", "name", "getName", "profile", "getProfile", "bind", "", "item", "Lcom/example/hischool/data/comment/CommentRecyclerViewData;", "setAllImage", "setOneImage", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.TextView name = null;
        private final android.widget.TextView message = null;
        @org.jetbrains.annotations.NotNull()
        private final android.content.Context mContext = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView profile = null;
        private final int mPostId = 0;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView imageList = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView imageList2 = null;
        @org.jetbrains.annotations.NotNull()
        private final androidx.fragment.app.FragmentManager mFragmentManager = null;
        
        public final android.widget.TextView getName() {
            return null;
        }
        
        public final android.widget.TextView getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Context getMContext() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getProfile() {
            return null;
        }
        
        public final int getMPostId() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImageList() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImageList2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.fragment.app.FragmentManager getMFragmentManager() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.hischool.data.comment.CommentRecyclerViewData item) {
        }
        
        private final void setOneImage(com.example.hischool.data.comment.CommentRecyclerViewData item) {
        }
        
        private final void setAllImage(com.example.hischool.data.comment.CommentRecyclerViewData item) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView, @org.jetbrains.annotations.NotNull()
        android.content.Context context, int postId, @org.jetbrains.annotations.NotNull()
        androidx.fragment.app.FragmentManager fragmentManager) {
            super(null);
        }
    }
}