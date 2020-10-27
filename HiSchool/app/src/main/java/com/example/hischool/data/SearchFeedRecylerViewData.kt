package com.example.hischool.data

data class SearchFeedRecylerViewData (
    val id : Int,
    val owner : SearchFeedRecylerViewDataOwner,
    val title : String,
    val text : String,
    val image_urls : String,
    val liked_peoples : ArrayList<Int>,
    val like_count : Int,
    val comment_count : Int,
    val created_at : String
)


