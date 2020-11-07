package com.example.hischool.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.paging.FeedDataSource

class FeedDataSourceFactory : DataSource.Factory<Int, FeedRecyclerViewData>() {

    private val mutableLiveData: MutableLiveData<PageKeyedDataSource<Int, FeedRecyclerViewData>> = MutableLiveData()

    override fun create(): DataSource<Int, FeedRecyclerViewData> {
        val feedDataSource = FeedDataSource()
        mutableLiveData.postValue(feedDataSource)
        return feedDataSource
    }

    internal fun getFeedLiveData(): MutableLiveData<PageKeyedDataSource<Int, FeedRecyclerViewData>>{
        return mutableLiveData
    }

}