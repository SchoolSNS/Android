package com.example.hischool.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.adapter.SearchFeedAdapter
import com.example.hischool.data.SearchFeedRecyclerViewData
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.collections.ArrayList as ArrayList

class SearchFragment : Fragment(){

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var mAdapter: SearchFeedAdapter
    var feedList: ArrayList<SearchFeedRecyclerViewData> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = SearchFeedAdapter(feedList, context)
        search_recyclerView.setHasFixedSize(true)
        search_recyclerView.adapter = mAdapter

        retrofit = RetrofitClient.getInstance()

        search_button.setOnClickListener {
            getFeedList()
        }
    }

    fun getFeedList() {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getSearchFeed(token = "Token ${Token.token}",page = 1, query = (search_search_edit.text.toString()))
            .enqueue(object : Callback<List<SearchFeedRecyclerViewData>> {
                override fun onResponse(
                    call: Call<List<SearchFeedRecyclerViewData>>,
                    response: Response<List<SearchFeedRecyclerViewData>>
                ) {
                    if(response.code() == 200) {
                        feedList = response.body() as ArrayList<SearchFeedRecyclerViewData>
                        Log.d("TAG", "data $feedList")
                        mAdapter.updateList(feedList)
                    }
                }

                override fun onFailure(call: Call<List<SearchFeedRecyclerViewData>>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }

            })
    }

}