package com.example.hischool.data.feed

data class FeedRecyclerViewData (
    val id : Int,
    val owner : FeedRecyclerViewDataOwner,
    val title : String,
    val content : String,
    val image_urls : ArrayList<String>,
    val like_peoples : ArrayList<Int>,
    var like_count : Int,
    var comment_count : Int,
    val created_at : String,
    val comment_preview : ArrayList<FeedCommentPreview>,
    var is_liked : Boolean
)