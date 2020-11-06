package com.example.hischool.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.data.FeedRecyclerViewData
import com.example.hischool.network.RetrofitClient
import com.example.hischool.network.Service
import com.example.hischool.view.activity.SelectSchoolActivity
import com.example.hischool.widget.startActivity
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FeedFragment : Fragment() {


    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var feedList : ArrayList<FeedRecyclerViewData>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = RetrofitClient.getInstance()
    }

    fun getFeed(){
        myAPI = retrofit.create(Service::class.java)
        myAPI.getFeed(token = "Token", page = 0).enqueue(object : Callback<List<FeedRecyclerViewData>>{
            override fun onResponse(call: Call<List<FeedRecyclerViewData>>, response: Response<List<FeedRecyclerViewData>>) {
                if(response.code() == 200)
                {
                    feedList = response.body() as ArrayList<FeedRecyclerViewData>
                    val mAdapter = FeedAdapter(feedList)
                    feed_recyclerView.setHasFixedSize(true)
                    feed_recyclerView.adapter = mAdapter
                }
            }

            override fun onFailure(call: Call<List<FeedRecyclerViewData>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}