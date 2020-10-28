package com.example.hischool.view.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.SearchFeedAdapter
import com.example.hischool.adapter.SelectSchoolAdapter
import com.example.hischool.data.SearchRecyclerViewData
import com.example.hischool.network.RetrofitClient
import com.example.hischool.network.Service
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_select_school.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.collections.ArrayList as ArrayList

class SearchFragment : Fragment() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var schoolList: ArrayList<SearchRecyclerViewData>


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

        search_search_edit.setOnEditorActionListener { v, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                return@setOnEditorActionListener true
            }
            false
        }
    }

}