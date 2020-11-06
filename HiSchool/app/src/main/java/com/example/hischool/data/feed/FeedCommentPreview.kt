package com.example.hischool.data.feed

data class FeedCommentPreview(
    val comment_id : Int,
    val owner : FeedCommentOwner,
    val content : String,
    val created_at : String,
    val image_urls : ArrayList<String>
)