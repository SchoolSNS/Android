package com.example.hischool.data

data class SearchFeedRecyclerViewData (
    val id : Int,
    val owner : SearchFeedRecyclerViewDataOwner,
    val title : String,
    val text : String,
    val image_urls : ArrayList<String>,
    val liked_peoples : ArrayList<Int>,
    var like_count : Int,
    val comment_count : Int,
    val created_at : String,
    var is_liked : Boolean
)


