package com.example.hischool.data.comment

data class CommentRecyclerViewData(
    val comment_id : Int,
    val owner : CommentOwner,
    val content : String,
    val created_at : String,
    val image_urls : ArrayList<String>
)