package com.example.hischool.paging

import androidx.recyclerview.widget.DiffUtil
import com.example.hischool.data.feed.FeedRecyclerViewData

class DiffUtilCallBack : DiffUtil.ItemCallback<FeedRecyclerViewData>() {

    override fun areItemsTheSame(oldItem: FeedRecyclerViewData, newItem: FeedRecyclerViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FeedRecyclerViewData, newItem: FeedRecyclerViewData): Boolean {
        return oldItem == newItem
    }

}