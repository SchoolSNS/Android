package com.example.hischool.view.activity

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hischool.R
import com.example.hischool.adapter.CommentAdapter
import com.example.hischool.adapter.CommentImagePreViewAdapter
import com.example.hischool.data.comment.CommentRecyclerViewData
import com.example.hischool.data.comment.WriteCommentResponse
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.ByteArrayOutputStream

class CommentActivity : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    private lateinit var commentImagePreViewAdapter: CommentImagePreViewAdapter

    private val imageList = ArrayList<Bitmap>()
    private val imageMultipart = ArrayList<RequestBody>()
    private val imageNameList = ArrayList<String>()

    lateinit var myAPI: Service
    lateinit var retrofit: Retrofit
    var commentList: ArrayList<CommentRecyclerViewData> = arrayListOf()

    private var postId: Int = 0
    private var imageCount = 0
    var count = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)


        postId = intent.getIntExtra("id", 0)
        retrofit = RetrofitClient.getInstance()
        getComment()

        comment_camera_btn.setOnClickListener {
            if(imageCount > 1)
            {
                Toast.makeText(applicationContext, "이미지는 최대 2개만 가능합니다", Toast.LENGTH_SHORT).show()
            }
            else {
                pickImageFromGallery()
            }
        }

        comment_post_btn.setOnClickListener {
            writeComment()
        }
    }

    private fun getComment() {
        myAPI = retrofit.create(Service::class.java)
        myAPI.getComment(token = "Token 719e203a89eaf9bd377a5e345da7da653d15492e", postId)
            .enqueue(object : Callback<List<CommentRecyclerViewData>> {
                override fun onResponse(
                    call: Call<List<CommentRecyclerViewData>>,
                    response: Response<List<CommentRecyclerViewData>>
                ) {
                    try {
                        commentList.clear()
                        commentList = response.body() as ArrayList<CommentRecyclerViewData>
                        Log.d("TAG", "data: $commentList")
                        val mAdapter = CommentAdapter(commentList, applicationContext)
                        comment_recyclerview.setHasFixedSize(true)
                        comment_recyclerview.adapter = mAdapter
                    } catch (e: NullPointerException) {
                        Log.d("TAG", "널포인텐트")
                        not_have_comment.text = "댓글이 존재하지 않습니다"
                    }
                }

                override fun onFailure(call: Call<List<CommentRecyclerViewData>>, t: Throwable) {

                }
            })

        commentImagePreViewAdapter = CommentImagePreViewAdapter(imageList, applicationContext)
        comment_image_recyclerview.adapter = commentImagePreViewAdapter
    }

    private fun writeComment() {

        if (comment_edit.text.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("TAG", comment_edit.text.toString())
                val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                builder.addFormDataPart("content", comment_edit.text.toString())

                imageMultipart.forEach {
                    Log.d("TAG", "count : $count")
                    builder.addFormDataPart(
                        "image$count",
                        imageNameList[count - 1],
                        it
                    )
                    count++
                }

                val postBody: RequestBody = builder.build()


                myAPI = retrofit.create(Service::class.java)
                myAPI.writeComment(
                    "Token 719e203a89eaf9bd377a5e345da7da653d15492e",
                    postBody,
                    postId
                ).enqueue(object : Callback<WriteCommentResponse> {
                    override fun onResponse(
                        call: Call<WriteCommentResponse>,
                        response: Response<WriteCommentResponse>
                    ) {
                        Log.d("TAG", response.code().toString())
                        if (response.code() == 201) {
                            comment_edit.setText("")
                            imageMultipart.clear()
                            imageList.clear()
                            imageNameList.clear()
                            count = 1
                            commentImagePreViewAdapter.notifyDataSetChanged()
                            getComment()
                        }
                    }

                    override fun onFailure(call: Call<WriteCommentResponse>, t: Throwable) {
                        Log.d("trowale", t.message.toString())
                        Log.d("ErrorBody", t.printStackTrace().toString())

                    }
                })
            }
        } else {
            Log.d("TAG", "없어")
            Toast.makeText(applicationContext, "내용이 없습니다.", Toast.LENGTH_SHORT).show()
        }

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
            Log.d("TAG", "하이")
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
            Log.d("TAG", "data $imageNameList")
            imageCount++
            returnCursor.close()
        }
    }
}