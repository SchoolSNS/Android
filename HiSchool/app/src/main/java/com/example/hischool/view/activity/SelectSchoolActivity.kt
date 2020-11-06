package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.CommentAdapter
import com.example.hischool.adapter.SelectSchoolAdapter
import com.example.hischool.data.CommentRecyclerViewData
import com.example.hischool.data.SelectSchoolRecyclerViewData
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_comment.comment_recyclerview
import kotlinx.android.synthetic.main.activity_select_school.*

class SelectSchoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_school)

        val selectSchoolList = arrayListOf(
            SelectSchoolRecyclerViewData(
                schoolName = "대구소프트웨어고",
                schoolAddress = "대구"
            )
        )
        select_school_recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        select_school_recyclerView.setHasFixedSize(true)

        select_school_recyclerView.adapter = SelectSchoolAdapter(selectSchoolList)
    }
}