package com.example.hischool.bottomSheet

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.data.feed.DelPostResponse
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.EditCommentActivity
import com.example.hischool.view.activity.EditFeedActivity
import com.example.hischool.view.activity.MainActivity
import com.example.hischool.view.fragment.FeedFragment
import com.example.hischool.widget.startActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_feed_bottom_sheet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FeedBottomSheet(val item : FeedRecyclerViewData, val callback : (FeedRecyclerViewData) -> Unit ) : BottomSheetDialogFragment() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_feed_bottom_sheet, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retrofit = RetrofitClient.getInstance()

        feed_del_btn.setOnClickListener {
            delPost()
            dismiss()
        }

        feed_edit_btn.setOnClickListener {
            dismiss()
            val intent = Intent(context, EditFeedActivity::class.java)
            intent.putExtra("text", item.content)
            intent.putExtra("urls", item.image_urls)
            intent.putExtra("postId", item.id)
            intent.putExtra("title", item.title)
            startActivity(intent)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    private fun delPost()
    {
        myAPI = retrofit.create(Service::class.java)
        myAPI.delPost(token = "Token ${Token.token}", postId = item.id).enqueue(
            object : Callback<DelPostResponse> {
                override fun onResponse(
                    call: Call<DelPostResponse>,
                    response: Response<DelPostResponse>
                ) {
                    Log.d("TAG", response.code().toString())
                    if (response.code() == 200) {
                        Toast.makeText(mContext, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        callback(item)
                    }
                }

                override fun onFailure(call: Call<DelPostResponse>, t: Throwable) {

                }

            })
    }

}