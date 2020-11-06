package com.example.hischool.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B\u001d\u0012\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u001e\u0010\u0014\u001a\u00020\r2\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006R!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0017"}, d2 = {"Lcom/example/hischool/adapter/SearchFeedAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/hischool/adapter/SearchFeedAdapter$ViewHolder;", "searchFeedList", "Ljava/util/ArrayList;", "Lcom/example/hischool/data/SearchFeedRecyclerViewData;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getSearchFeedList", "()Ljava/util/ArrayList;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "list", "ViewHolder", "app_debug"})
public final class SearchFeedAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.hischool.adapter.SearchFeedAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.example.hischool.data.SearchFeedRecyclerViewData> searchFeedList = null;
    
    public final void updateList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.hischool.data.SearchFeedRecyclerViewData> list) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.hischool.adapter.SearchFeedAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.hischool.adapter.SearchFeedAdapter.ViewHolder holder, int position) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.example.hischool.data.SearchFeedRecyclerViewData> getSearchFeedList() {
        return null;
    }
    
    public SearchFeedAdapter(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<com.example.hischool.data.SearchFeedRecyclerViewData> searchFeedList) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0019\u0010\f\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u0019\u0010\u0016\u001a\n \u0007*\u0004\u0018\u00010\u00170\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\tR\u0019\u0010\u001c\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\tR\u0019\u0010\u001e\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\t\u00a8\u0006 "}, d2 = {"Lcom/example/hischool/adapter/SearchFeedAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "content", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getContent", "()Landroid/widget/TextView;", "count_heart", "getCount_heart", "count_message", "getCount_message", "heartState", "", "getHeartState", "()Z", "setHeartState", "(Z)V", "liked", "getLiked", "profile", "Landroid/widget/ImageView;", "getProfile", "()Landroid/widget/ImageView;", "time", "getTime", "title", "getTitle", "username", "getUsername", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.ImageView profile = null;
        private final android.widget.TextView username = null;
        private final android.widget.TextView time = null;
        private final android.widget.TextView title = null;
        private final android.widget.TextView content = null;
        private final android.widget.TextView liked = null;
        private final android.widget.TextView count_heart = null;
        private final android.widget.TextView count_message = null;
        private boolean heartState = false;
        
        public final android.widget.ImageView getProfile() {
            return null;
        }
        
        public final android.widget.TextView getUsername() {
            return null;
        }
        
        public final android.widget.TextView getTime() {
            return null;
        }
        
        public final android.widget.TextView getTitle() {
            return null;
        }
        
        public final android.widget.TextView getContent() {
            return null;
        }
        
        public final android.widget.TextView getLiked() {
            return null;
        }
        
        public final android.widget.TextView getCount_heart() {
            return null;
        }
        
        public final android.widget.TextView getCount_message() {
            return null;
        }
        
        public final boolean getHeartState() {
            return false;
        }
        
        public final void setHeartState(boolean p0) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}