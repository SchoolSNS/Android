package com.example.hischool.widget

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hischool.R
import com.example.hischool.adapter.CommentAdapter
import com.example.hischool.data.comment.CommentRecyclerViewData
import com.example.hischool.data.comment.CommentUpdateResponse
import com.example.hischool.data.feed.DelPostResponse
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.CommentActivity
import com.example.hischool.view.activity.EditCommentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_comment_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_feed_bottom_sheet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CommentBottomSheet(val item : CommentRecyclerViewData, val postId: Int, val callback : (Boolean) -> Unit) : BottomSheetDialogFragment() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_comment_bottom_sheet, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrofit = RetrofitClient.getInstance()
        comment_del_btn.setOnClickListener {
            delComment()
            dismiss()
        }

        comment_edit_btn.setOnClickListener {

            dismiss()
            val intent = Intent(mContext, EditCommentActivity::class.java)
            intent.putExtra("text", item.content)
            intent.putExtra("urls", item.image_urls)
            startActivity(intent)
        }
    }

    private fun delComment()
    {
        myAPI = retrofit.create(Service::class.java)
        myAPI.delComment(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e", postId = postId, commentId = item.comment_id).enqueue(
            object : Callback<CommentUpdateResponse> {
                override fun onResponse(
                    call: Call<CommentUpdateResponse>,
                    response: Response<CommentUpdateResponse>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(mContext, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        callback(true)
                    }
                    else{
                        callback(false)
                    }
                }

                override fun onFailure(call: Call<CommentUpdateResponse>, t: Throwable) {
                    callback(false)
                }

            })
    }
}