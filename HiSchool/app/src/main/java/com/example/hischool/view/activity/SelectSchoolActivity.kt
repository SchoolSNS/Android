package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.example.hischool.sharedpreferences.App.Companion.prefs
import com.example.hischool.R
import com.example.hischool.adapter.SelectSchoolAdapter
import com.example.hischool.data.SearchSchoolRecyclerViewData
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.activity_select_school.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SelectSchoolActivity : AppCompatActivity() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var mAdapter: SelectSchoolAdapter
    var schoolList: ArrayList<SearchSchoolRecyclerViewData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_school)

        mAdapter = SelectSchoolAdapter(schoolList)
        select_school_recyclerView.setHasFixedSize(true)
        select_school_recyclerView.adapter = mAdapter

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

        select_school_next_button.setOnClickListener {
            if(mAdapter.selected > -1) {
                Log.d("TAG", "SELECTED INDEX : ${mAdapter.selected}, list Item : ${schoolList[mAdapter.selected].name}")
                prefs.schoolEditText = schoolList[mAdapter.selected].name;
                Log.d("TAG", "CURRENT VALUE : ${prefs.schoolEditText}")
                finish()
            }
        }


    }

    fun getSchoolList() {
        myAPI = retrofit.create(Service::class.java)
        Log.d("TAG", "schoolName : ${select_search_edit.text.toString()}")
        myAPI.getSearchSchool(page = 1, search = (select_search_edit.text.toString()).trim())
            .enqueue(object : Callback<List<SearchSchoolRecyclerViewData>> {
                override fun onResponse(
                    call: Call<List<SearchSchoolRecyclerViewData>>,
                    response: Response<List<SearchSchoolRecyclerViewData>>
                ) {
                    if (response.code() == 200) {
                        schoolList = response.body() as ArrayList<SearchSchoolRecyclerViewData>
                        Log.d("TAG", "data $schoolList")
                        mAdapter.updateList(schoolList)
                    }
                }

                override fun onFailure(call: Call<List<SearchSchoolRecyclerViewData>>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }

            })
    }


}