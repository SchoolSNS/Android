package com.example.hischool.data

import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.feed.FeedRecyclerViewDataOwner

data class NotificationRecyclerViewData (
    val post : FeedRecyclerViewData,
    val notification_title : String
)
