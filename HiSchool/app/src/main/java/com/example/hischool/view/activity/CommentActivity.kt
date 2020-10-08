package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.CommentAdapter
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.data.CommentRecyclerViewData
import com.example.hischool.data.FeedRecyclerViewData
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.fragment_feed.*

class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val commentList = arrayListOf(
            CommentRecyclerViewData(
                name = "이문영",
                messsage = "안녕하세연"
            )
        )
        comment_recyclerview.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        comment_recyclerview.setHasFixedSize(true)

        comment_recyclerview.adapter = CommentAdapter(commentList)

        comment_post_bnt.setOnClickListener {
            commentList.add(CommentRecyclerViewData(name = "이문영", messsage = comment_edit.text.toString()))
            (comment_recyclerview.adapter as CommentAdapter).notifyDataSetChanged()
            comment_edit.setText("")
        }
    }
}