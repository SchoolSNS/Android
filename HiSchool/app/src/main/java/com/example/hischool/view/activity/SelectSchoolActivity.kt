package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.SelectSchoolAdapter
import com.example.hischool.data.SearchRecyclerViewData
import com.example.hischool.network.RetrofitClient
import com.example.hischool.network.Service
import kotlinx.android.synthetic.main.activity_select_school.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SelectSchoolActivity : AppCompatActivity() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var schoolList: ArrayList<SearchRecyclerViewData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_school)

        retrofit = RetrofitClient.getInstance()

        select_search_edit.setOnKeyListener { v, keyCode, event ->
            when {
                ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.action == KeyEvent.ACTION_DOWN)) -> {
                    getSchoolList()
                    return@setOnKeyListener true
                }
                else -> false
            }
        }
    }

    fun getSchoolList() {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getSearchFeed(page = 1, query = select_search_edit.text.toString())
            .enqueue(object : Callback<List<SearchRecyclerViewData>> {
                override fun onResponse(
                    call: Call<List<SearchRecyclerViewData>>,
                    response: Response<List<SearchRecyclerViewData>>
                ) {
                    if (response.code() == 200) {
                        schoolList = response.body() as ArrayList<SearchRecyclerViewData>
                        Log.d("TAG", "data $schoolList")
                        val mAdapter = SelectSchoolAdapter(schoolList)
                        select_school_recyclerView.setHasFixedSize(true)
                        select_school_recyclerView.adapter = mAdapter
                    }
                }

                override fun onFailure(call: Call<List<SearchRecyclerViewData>>, t: Throwable) {
                    Log.d("TAG", "들어았다")
                    Log.d("TAG", t.message.toString())
                }

            })
    }
}