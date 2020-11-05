package com.example.hischool.bottomSheet

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hischool.R
import com.example.hischool.data.feed.DelPostResponse
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.EditFeedActivity
import com.example.hischool.view.activity.MainActivity
import com.example.hischool.widget.startActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_detail_feed_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_feed_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_feed_bottom_sheet.feed_edit_btn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DetailFeedBottomSheet (val content : String, val imageUrls : ArrayList<String>, val postId : Int, val title : String, val callback : () -> Unit) : BottomSheetDialogFragment() {
    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_detail_feed_bottom_sheet, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retrofit = RetrofitClient.getInstance()

        detail_feed_del_btn.setOnClickListener {
            delPost()
            dismiss()
        }

        detail_feed_edit_btn.setOnClickListener {
            dismiss()
            val intent = Intent(context, EditFeedActivity::class.java)
            intent.putExtra("text", content)
            intent.putExtra("urls", imageUrls)
            intent.putExtra("postId", postId)
            intent.putExtra("title", title)
            startActivity(intent)
        }
    }

    private fun delPost()
    {
        myAPI = retrofit.create(Service::class.java)
        myAPI.delPost(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e", postId = postId).enqueue(
            object : Callback<DelPostResponse> {
                override fun onResponse(
                    call: Call<DelPostResponse>,
                    response: Response<DelPostResponse>
                ) {
                    Log.d("TAG", response.code().toString())
                    if (response.code() == 200) {
                        Toast.makeText(mContext, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        callback()
                    }
                }

                override fun onFailure(call: Call<DelPostResponse>, t: Throwable) {

                }

            })
    }
}