package com.example.hischool.view.fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.SearchAdapter
import com.example.hischool.data.SearchRecyclerViewData
import com.example.hischool.data.SelectSchoolRecyclerViewData
import com.example.hischool.network.RetrofitClient
import com.example.hischool.network.Service
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_select_school.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SearchFragment : Fragment() {

    lateinit var myAPI : Service
    lateinit var retrofit: Retrofit
    lateinit var schoolList: ArrayList<SelectSchoolRecyclerViewData>


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = RetrofitClient.getInstance()

        select_school_recyclerView.layoutManager = LinearLayoutManager(activity)

        search_search_edit.setOnKeyListener { v, keyCode, event ->

            when {
                ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) -> {


                    return@setOnKeyListener true
                }
                else -> false
            }


        }
    }

    fun getSchoolList() {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getSearchFeed(token = "Token bb6847b4ed62374e7b8778d071f8eb669b3b2a42", page = 1, query = search_search_edit.text.toString()).enqueue(object : Callback<SearchRecyclerViewData> {
            override fun onResponse(call: Call<SearchRecyclerViewData>, response: Response<SearchRecyclerViewData>) {

            }

            override fun onFailure(call: Call<SearchRecyclerViewData>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}