package com.example.hischool.data

data class FeedRecyclerViewData (
    val id : Int,
    val owner : FeedRecyclerViewDataOwner,
    val title : String,
    val text : String,
    val image_urls : String,
    val like_peoples : ArrayList<Int>,
    val like_count : Int,
    val comment_count : Int,
    val created_at : String
)