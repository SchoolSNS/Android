package com.example.hischool.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FeedDataSource : PageKeyedDataSource<Int, FeedRecyclerViewData>() {

    internal val firstPage = 1
    lateinit var myAPI : Service
    lateinit var retrofit: Retrofit
    private var  feedList = ArrayList<FeedRecyclerViewData>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, FeedRecyclerViewData>) {
        retrofit = RetrofitClient.getInstance()
        myAPI = retrofit.create(Service::class.java)
        myAPI.getFeed(token = "Token ${Token.token}", page = firstPage).enqueue(object :
            Callback<List<FeedRecyclerViewData>> {
            override fun onResponse(call: Call<List<FeedRecyclerViewData>>, response: Response<List<FeedRecyclerViewData>>) {
                if(response.code() == 200)
                {
                    feedList = response.body() as ArrayList<FeedRecyclerViewData>
                    Log.d("TAG", "data $feedList")
                    callback.onResult(feedList, null, firstPage + 1)
                }
                Log.d("TAG", response.code().toString())
            }

            override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                Log.d("TAG", "fail : ${t.message.toString()}")
            }

        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, FeedRecyclerViewData>) {
        retrofit = RetrofitClient.getInstance()
        myAPI = retrofit.create(Service::class.java)
        myAPI.getFeed(token = "Token ${Token.token}", page = params.key).enqueue(object :
                Callback<List<FeedRecyclerViewData>> {
            override fun onResponse(call: Call<List<FeedRecyclerViewData>>, response: Response<List<FeedRecyclerViewData>>) {
                if(response.code() == 200)
                {
                    feedList = response.body() as ArrayList<FeedRecyclerViewData>

                    val key: Int? = if (params.key > 1) {
                        params.key - 1
                    } else {
                        null
                    }

                    callback.onResult(feedList, key)
                }
                Log.d("TAG", response.code().toString())
            }

            override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                Log.d("TAG", "fail : ${t.message.toString()}")
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, FeedRecyclerViewData>) {
        retrofit = RetrofitClient.getInstance()
        myAPI = retrofit.create(Service::class.java)
        myAPI.getFeed(token = "Token ${ Token.token}", page = params.key).enqueue(object :
                Callback<List<FeedRecyclerViewData>> {
            override fun onResponse(call: Call<List<FeedRecyclerViewData>>, response: Response<List<FeedRecyclerViewData>>) {
                if (response.code() == 200) {
                    feedList = response.body() as ArrayList<FeedRecyclerViewData>

                    if (feedList.size % 15 == 0) {
                        val key: Int? = if (params.key <= 1) {
                            params.key + 1
                        } else {
                            null
                        }

                        callback.onResult(feedList, key)
                    }
                    Log.d("TAG", response.code().toString())
                }
            }

            override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                Log.d("TAG", "fail : ${t.message.toString()}")
            }
        })
    }

}