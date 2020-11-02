package com.example.hischool.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.widget.FeedBottomSheet
import kotlinx.android.synthetic.main.feed_item.*
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

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = RetrofitClient.getInstance()
        getFeed()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    private fun getFeed(){
        myAPI = retrofit.create(Service::class.java)
        myAPI.getFeed(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e", page = 1).enqueue(object : Callback<List<FeedRecyclerViewData>>{
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
                Log.d("TAG", t.message.toString())
            }

        })


    }
}