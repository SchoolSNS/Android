package com.example.hischool.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.paging.FeedDataSource
import com.example.hischool.paging.FeedDataSourceFactory

class FeedViewModel : ViewModel() {
    private val feedDataSource = FeedDataSource()
    val feedPagedList: LiveData<PagedList<FeedRecyclerViewData>>

    private val liveDataSource: LiveData<PageKeyedDataSource<Int, FeedRecyclerViewData>>

    init {
        val feedDataSourceFactory = FeedDataSourceFactory()
        liveDataSource = feedDataSourceFactory.getFeedLiveData()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(feedDataSource.firstPage)
            .build()

        feedPagedList = LivePagedListBuilder(feedDataSourceFactory, config).build()
    }
}