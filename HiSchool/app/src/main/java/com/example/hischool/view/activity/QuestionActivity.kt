package com.example.hischool.view.activity

import android.Manifest
import android.app.Activity
import android.content.ContentResolver
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
import com.example.hischool.R
import com.example.hischool.data.PostResponse
import com.example.hischool.module.RotateImage
import com.example.hischool.network.RetrofitClient
import com.example.hischool.network.Service
import com.example.hischool.widget.startActivity
import com.example.hischool.widget.toast
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import okio.Okio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class QuestionActivity : AppCompatActivity() {
    var imageList = ArrayList<RequestBody>()
    private val rotateImageClass = RotateImage()
    private val imageNameList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        question_image.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    //permission already granted
                    pickImageFromGallery();
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery();
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
                        addFormDataPart("image${index+1}", imageNameList[index] , it)
                        index++
                    }
                }.build()

                val myAPI =RetrofitClient.getInstance().create(Service::class.java)
                myAPI.requestPost("Token e20082c2f7293ed20a5525015e0b0b9e29944b3a", body).enqueue(object : Callback<PostResponse> {
                    override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                        Log.d("TAG", "FAILED WITH ERROR")
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>
                    ) {
                        Log.d("TAG", "Success!");
                        Log.d("TAG", response.code().toString())
                        response.body()?.let {
                            postResponse ->  postResponse.message.forEach{
                                Log.d("TAG", it)
                            }
                        }
                        response.errorBody()?.let {
                            Log.d("TAG", it.string())
                        }
                        call.request().headers().names().forEach {
                            Log.d("TAG", "NAME : $it, value : ${call.request().headers().get(it)}")
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

        //Permission code
        private const val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //이미지 정보
        val returnUri: Uri
        val returnCursor: Cursor?

        if(resultCode == RESULT_OK) {
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

                    inputStream?.close()

                    returnCursor = contentResolver.query(returnUri, null, null, null, null)

                    //이미지 이름
                    val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    imageNameList.add(returnCursor.getString(nameIndex))

                    returnCursor.close()

                }

                else -> {
                    Toast.makeText(this, "이미지를 제대로 가져오지 못하였습니다.", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}