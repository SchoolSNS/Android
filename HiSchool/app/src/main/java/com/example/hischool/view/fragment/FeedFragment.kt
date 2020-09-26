package com.example.hischool.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.data.FeedRecyclerViewData
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val feedList = arrayListOf(
            FeedRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 세연",
                "58분전",
                "안녕하세연?",
                "안녕히가세연",
                10,
                10)
        )
        feed_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        feed_recyclerView.setHasFixedSize(true)
        feed_recyclerView.adapter = FeedAdapter(feedList)

        school_name_text.setOnClickListener {

        }
    }
}