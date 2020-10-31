package com.example.hischool.widget

import android.graphics.drawable.DrawableContainer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hischool.R
import com.example.hischool.data.feed.DelPostResponse
import com.example.hischool.network.retrofit.Service
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_feed_bottom_sheet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FeedBottomSheet(val postId : Int) : BottomSheetDialogFragment() {

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.activity_feed_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        feed_del_btn.setOnClickListener {
            delPost()
            dismiss()
        }

        feed_edit_btn.setOnClickListener {

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
                    if (response.code() == 200) {
                        Toast.makeText(context, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<DelPostResponse>, t: Throwable) {

                }

            })
    }

}