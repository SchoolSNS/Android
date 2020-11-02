package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hischool.R
import com.example.hischool.adapter.CommentImagePreViewAdapter
import com.example.hischool.adapter.EditCommentSetImageAdapter
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_edit_comment.*
import retrofit2.Retrofit
import java.io.Serializable

class EditCommentActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI : Service
    lateinit var content : String
    lateinit var imageUrls : ArrayList<String>
    private lateinit var editCommentSetImageAdapter: EditCommentSetImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_comment)

        retrofit = RetrofitClient.getInstance()
        content = intent.getStringExtra("text").toString()
        imageUrls = intent.getSerializableExtra("urls") as ArrayList<String>

        comment_edit_edit.setText(content)

        editCommentSetImageAdapter = EditCommentSetImageAdapter(imageUrls, applicationContext)
        comment_edit_image_recyclerview.adapter = editCommentSetImageAdapter

        comment_edit_post_btn.setOnClickListener {
            updateComment()
        }
    }

    fun updateComment()
    {
        myAPI = retrofit.create(Service::class.java)
    }
}