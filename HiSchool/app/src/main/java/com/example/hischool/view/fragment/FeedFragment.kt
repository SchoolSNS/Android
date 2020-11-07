package com.example.hischool.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.fragment_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FeedFragment : Fragment() {


    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    var feedList : ArrayList<FeedRecyclerViewData> = arrayListOf()
    lateinit var mContext : Context

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "onCreateView 호출됨")
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onResume() {
        Log.d("TAG", "onResume 호출됨")
        retrofit = RetrofitClient.getInstance()
        getFeed()
        super.onResume()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        Log.d("TAG", "onAttach")
    }

    fun getFeed(){
        myAPI = retrofit.create(Service::class.java)
        myAPI.getFeed(token = "Token ${Token.token}", page = 1).enqueue(object : Callback<List<FeedRecyclerViewData>>{
            override fun onResponse(call: Call<List<FeedRecyclerViewData>>, response: Response<List<FeedRecyclerViewData>>) {
                if(response.code() == 200)
                {
                    feedList.clear()
                    feedList = response.body() as ArrayList<FeedRecyclerViewData>
                    Log.d("TAG", "data $feedList")
                    val mAdapter = FeedAdapter(feedList, mContext)
                    feed_recyclerView.setHasFixedSize(true)
                    feed_recyclerView.adapter = mAdapter
                }
                Log.d("TAG", response.code().toString())
            }

            override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                Log.d("TAG", "fail : ${t.message.toString()}")
            }

        })


    }
}