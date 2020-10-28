package com.example.hischool.view.activity

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hischool.R
import com.example.hischool.adapter.CommentAdapter
import com.example.hischool.adapter.CommentImagePreViewAdapter
import com.example.hischool.adapter.FeedAdapter
import com.example.hischool.data.comment.CommentRecyclerViewData
import com.example.hischool.data.comment.WriteCommentResponse
import com.example.hischool.data.feed.FeedRecyclerViewData
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.fragment_feed.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.ByteArrayOutputStream
import java.lang.NullPointerException

class CommentActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    private lateinit var commentImagePreViewAdapter : CommentImagePreViewAdapter

    private val imageList = ArrayList<Bitmap>()
    private val imageMultipart = ArrayList<RequestBody>()
    private val imageNameList = ArrayList<String>()

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    lateinit var commentList : ArrayList<CommentRecyclerViewData>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        retrofit = RetrofitClient.getInstance()
        getComment()

        comment_camera_btn.setOnClickListener {
            pickImageFromGallery()
        }

        comment_post_btn.setOnClickListener {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart("content", comment_edit.text.toString())
            builder.addFormDataPart("image", "commentImage", RequestBody.create(MultipartBody.FORM, "파일 경로"))

            val postBody: RequestBody = builder.build()

            myAPI = retrofit.create(Service::class.java)
            myAPI.writeComment("Token 719e203a89eaf9bd377a5e345da7da653d15492e", postBody).enqueue(object : Callback<WriteCommentResponse>{
                override fun onResponse(
                    call: Call<WriteCommentResponse>,
                    response: Response<WriteCommentResponse>
                ) {
                    if(response.code() == 200)
                    {

                    }
                }

                override fun onFailure(call: Call<WriteCommentResponse>, t: Throwable) {

                }

            })
        }
    }

    private fun getComment()
    {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getComment(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e").enqueue(object : Callback<List<CommentRecyclerViewData>>{
            override fun onResponse(
                call: Call<List<CommentRecyclerViewData>>,
                response: Response<List<CommentRecyclerViewData>>
            ) {
                try {
                    commentList = response.body() as ArrayList<CommentRecyclerViewData>
                    Log.d("TAG", "data: $commentList")
                    val mAdapter = CommentAdapter(commentList)
                    comment_recyclerview.setHasFixedSize(true)
                    comment_recyclerview.adapter = mAdapter
                }catch(e : NullPointerException){
                    not_have_comment.text = "댓글이 존재하지 않습니다"
                }
            }
            override fun onFailure(call: Call<List<CommentRecyclerViewData>>, t: Throwable) {

            }
        })

        commentImagePreViewAdapter = CommentImagePreViewAdapter(imageList, applicationContext)
        comment_image_recyclerview.adapter = commentImagePreViewAdapter
    }

    private fun writeComment()
    {
        myAPI = retrofit.create(Service::class.java)

    }

    fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val returnUri: Uri
        val returnCursor: Cursor?

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            returnUri = data?.data!!

            val inputStream = contentResolver.openInputStream(returnUri)
            var bm: Bitmap = BitmapFactory.decodeStream(inputStream) //비트맵 변환
            val bos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            imageMultipart.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))

            inputStream?.close()

            imageList.add(bm)
            commentImagePreViewAdapter.notifyDataSetChanged()

            returnCursor = contentResolver.query(returnUri, null, null, null, null)

            //이미지 이름
            val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            imageNameList.add(returnCursor.getString(nameIndex))

            returnCursor.close()
        }
    }
}