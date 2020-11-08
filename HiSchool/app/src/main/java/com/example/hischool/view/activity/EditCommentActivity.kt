package com.example.hischool.view.activity

import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.hischool.R
import com.example.hischool.adapter.CommentImagePreViewAdapter
import com.example.hischool.adapter.EditCommentSetImageAdapter
import com.example.hischool.data.comment.CommentUpdateResponse
import com.example.hischool.data.login.Token
import com.example.hischool.module.RotateImage
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_edit_comment.*
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
import java.io.Serializable

class EditCommentActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI : Service
    lateinit var content : String
    lateinit var imageUrls : ArrayList<String>
    private val rotateImageClass = RotateImage()
    var postId : Int = 0
    var commentId : Int = 0
    val imageBitmap : ArrayList<Bitmap> = arrayListOf()
    private val imageMultipart = ArrayList<RequestBody>()
    val imageNameList : ArrayList<String> = arrayListOf()

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    private lateinit var editCommentSetImageAdapter: EditCommentSetImageAdapter

    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_comment)

        retrofit = RetrofitClient.getInstance()
        content = intent.getStringExtra("text").toString()
        imageUrls = intent.getSerializableExtra("urls") as ArrayList<String>
        postId = intent.getIntExtra("postId", 0)
        commentId = intent.getIntExtra("commentId", 0)

        editCommentSetImageAdapter = EditCommentSetImageAdapter(imageBitmap, applicationContext)
        comment_edit_image_recyclerview.adapter = editCommentSetImageAdapter

        imageUrls.forEach {
            Glide.with(applicationContext).asBitmap().load(it).into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    imageBitmap.add(resource)
                    imageNameList.add("imageis123$count.jpg")
                    val bos = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                    imageMultipart.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))
                    editCommentSetImageAdapter.notifyDataSetChanged()
                    Log.d("TAG", "data $imageNameList")
                    Log.d("TAG", "data $imageMultipart")
                    print("하이")
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        }


        comment_edit_edit.setText(content)



        comment_edit_post_btn.setOnClickListener {
            updateComment()
        }

        comment_edit_camera_btn.setOnClickListener {
            if(imageBitmap.size < 2) {
                pickImageFromGallery()
            }
            else{
                Toast.makeText(applicationContext, "사진은 최대 2개입니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateComment()
    {
        if(comment_edit_edit.text.isNotEmpty())
        {
            CoroutineScope(Dispatchers.IO).launch {
                val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                builder.addFormDataPart("content", comment_edit_edit.text.toString())
                imageMultipart.forEach {
                    Log.d("TAG", "count : $count")
                    Log.d("TAG", "name : ${imageNameList[count - 1]}")
                    Log.d("TAG", "fileData : $it")
                    Log.d("TAG", "all File : $imageMultipart")
                    builder.addFormDataPart(
                        "image$count",
                        imageNameList[count - 1],
                        it
                    )
                    count++
                }

                val postBody: RequestBody = builder.build()
                myAPI = retrofit.create(Service::class.java)

                myAPI.updateComment("Token ${Token.token}",postId, commentId, postBody).enqueue(object : Callback<CommentUpdateResponse>{
                    override fun onResponse(
                        call: Call<CommentUpdateResponse>,
                        response: Response<CommentUpdateResponse>
                    ) {
                        if(response.code() == 200)
                        {
                            finish()
                        }
                        Log.d("TAG", response.code().toString())
                        Log.d("TAG", response.message())
                    }

                    override fun onFailure(call: Call<CommentUpdateResponse>, t: Throwable) {
                        Log.d("TAG", t.message.toString())
                    }

                })
            }
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
            bm = rotateImageClass.rotateImage(data.data!!, bm, contentResolver)
            val bos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            imageMultipart.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))

            inputStream?.close()

            imageBitmap.add(bm)
            editCommentSetImageAdapter.notifyDataSetChanged()

            returnCursor = contentResolver.query(returnUri, null, null, null, null)

            //이미지 이름
            val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            imageNameList.add(returnCursor.getString(nameIndex))
            Log.d("TAG", "data $imageNameList")
            returnCursor.close()
        }
    }
}