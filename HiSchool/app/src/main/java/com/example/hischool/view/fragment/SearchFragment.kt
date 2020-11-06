package com.example.hischool.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.SearchAdapter
import com.example.hischool.data.SearchRecyclerViewData
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_item.*

class SearchFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchList = arrayListOf(
            SearchRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 족발",
                "1분전",
                "대구 소프트웨어 고등학교 좋나요?",
                "좋나요?",
                10,
                10),
            SearchRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 문영",
                "11분전",
                "문영이는 바보인가요?",
                "맞나요?",
                1,
                0),
            SearchRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 규락",
                "21분전",
                "규락이는 다이어트 중인가요?",
                "아니던데요?",
                2,
                10),
            SearchRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 초현",
                "30분전",
                "태국 언제 다녀왔나요?",
                "제발 알려주세요",
                4,
                20),
            SearchRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 준환",
                "44분전",
                "주나니는 나나요?",
                "나나요?",
                10,
                10),
            SearchRecyclerViewData(R.drawable.main_round_image,
                "지나가는 익명의 세연",
                "58분전",
                "안녕하세연?",
                "안녕히가세연",
                10,
                10)
        )
        search_recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        search_recyclerView.setHasFixedSize(true)

        search_recyclerView.adapter = SearchAdapter(searchList )
    }


}