package com.example.hischool.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hischool.R
import com.example.hischool.adapter.EditFeedSetImageAdapter
import com.example.hischool.data.PostResponse
import com.example.hischool.data.login.Token
import com.example.hischool.module.RotateImage
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.widget.startActivity
import com.example.hischool.widget.toast
import kotlinx.android.synthetic.main.activity_question.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class QuestionActivity : AppCompatActivity() {
    var imageList = ArrayList<RequestBody>()
    private val rotateImageClass = RotateImage()
    private val imageNameList = ArrayList<String>()
    private var imageBitmap = ArrayList<Bitmap>()

    lateinit var questionImageAdapter: EditFeedSetImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        questionImageAdapter = EditFeedSetImageAdapter(imageBitmap, applicationContext)
        question_image_recyclerview.adapter = questionImageAdapter


        question_image.setOnClickListener {
            if (imageBitmap.size < 5) {
                Log.d("TAG", imageBitmap.size.toString())
                Log.d("TAG", "눌림")
                pickImageFromGallery()
            } else {
                Toast.makeText(applicationContext, "이미지는 5개가 최대입니다.", Toast.LENGTH_SHORT).show()
            }
        }

        question_submit.setOnClickListener {
            if (TextUtils.isEmpty(question_title.text)) {
                toast("제목을 설정해주세요")
            } else if (TextUtils.isEmpty(question_contents.text)) {
                toast("내용을 입력해주세요");
            } else {
                val body = MultipartBody.Builder().setType(MultipartBody.FORM).apply {
                    addFormDataPart("title", question_title.text.toString())
                    addFormDataPart("content", question_contents.text.toString())
                    var index = 0
                    imageList.forEach {
                        addFormDataPart("image${index + 1}", imageNameList[index], it)
                        index++
                    }
                }.build()

                val myAPI = RetrofitClient.getInstance().create(Service::class.java)

                myAPI.requestPost("Token ${Token.token}", body)
                    .enqueue(object : Callback<PostResponse> {
                        override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                            Log.d("TAG", "FAILED WITH ERROR")
                            t.printStackTrace()
                        }

                        override fun onResponse(
                            call: Call<PostResponse>, response: Response<PostResponse>
                        ) {
                            Log.d("TAG", "Success!");
                            Log.d("TAG", response.code().toString())
                            if (response.code() == 201) {
                                startActivity(MainActivity::class.java)
                            }
                        }
                    })
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity::class.java)
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000;
    }

    //handle result of picked image
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //이미지 정보
        val returnUri: Uri
        val returnCursor: Cursor?

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                IMAGE_PICK_CODE -> {

                    //이미지 정보
                    returnUri = data?.data!!//이미지 커서

                    //이미지 파일 받아오기
                    val inputStream = contentResolver.openInputStream(returnUri) //input 스트림
                    var bm: Bitmap = BitmapFactory.decodeStream(inputStream) //비트맵 변환
                    bm = rotateImageClass.rotateImage(data.data!!, bm, contentResolver) //이미지 회전
                    val bos = ByteArrayOutputStream()
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                    imageList.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))

                    imageBitmap.add(bm)
                    questionImageAdapter.notifyDataSetChanged()

                    inputStream?.close()

                    returnCursor = contentResolver.query(returnUri, null, null, null, null)

                    //이미지 이름
                    val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    imageNameList.add(returnCursor.getString(nameIndex))

                    returnCursor.close()


                    Glide.with(this)
                        .load(returnUri)

                }

                else -> {
                    Toast.makeText(this, "이미지를 제대로 가져오지 못하였습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
