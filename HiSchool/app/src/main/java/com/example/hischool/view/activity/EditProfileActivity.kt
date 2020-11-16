package com.example.hischool.view.activity

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.hischool.R
import com.example.hischool.data.IntroduceBody
import com.example.hischool.data.SuccessString
import com.example.hischool.data.feed.DelPostResponse
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.module.RotateImage
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.LoginData
import com.example.hischool.room.LoginDataBase
import com.example.hischool.view.fragment.SignInFragment
import com.example.hischool.widget.startActivity
import kotlinx.android.synthetic.main.activity_edit_feed.*
import kotlinx.android.synthetic.main.activity_edit_profile.*
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

class EditProfileActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI: Service

    var userName: String = ""
    var userSchool: String = ""
    var userIntroduce: String = ""
    var userProfile: String = ""

    var count = 1

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    lateinit var imageBitmap: Bitmap
    private val imageMultipart = ArrayList<RequestBody>()
    var imageName: String = ""
    private val rotateImageClass = RotateImage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        retrofit = RetrofitClient.getInstance()
        getData()
        edit_profile_constraint.setOnClickListener {
            pickImageFromGallery()
        }
        edit_profile_btn.setOnClickListener {
            getUserProfile()
        }
    }


    fun getData() {
        userName = intent.getStringExtra("userName")!!
        userSchool = intent.getStringExtra("userSchool")!!
        userIntroduce = intent.getStringExtra("userIntroduce")!!
        userProfile = intent.getStringExtra("userProfile")!!
        setData()
    }

    fun setData() {
        edit_profile_user_name.text = userName
        edit_profile_user_school.text = userSchool
        Glide.with(applicationContext)
            .load(userProfile)
            .transform(CenterCrop(), RoundedCorners(10000))
            .into(edit_profile_user_profile)
        edit_profile_user_introduce.setText(userIntroduce)
    }

    fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.N)
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

            imageBitmap = bm

            Glide.with(applicationContext)
                .load(imageBitmap)
                .transform(CenterCrop(), RoundedCorners(10000))
                .into(edit_profile_user_profile)

            returnCursor = contentResolver.query(returnUri, null, null, null, null)

            //이미지 이름
            val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            imageName = returnCursor.getString(nameIndex)
            Log.d("TAG", "data $imageName")
            returnCursor.close()
        }
    }

    fun getUserProfile() {
        if(imageMultipart.isEmpty())
        {
            Log.d("TAG", "하ㅍㅍ이")
            Glide.with(applicationContext).asBitmap().load(LoginInformation.loginInfoData.profile).into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    imageBitmap = resource
                    imageName = "image.jpg"
                    val bos = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                    imageMultipart.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))
                    Log.d("TAG", "data $imageMultipart")
                    updateUserData();
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
        }else updateUserData()


    }

    fun updateUserData(){
        CoroutineScope(Dispatchers.IO).launch {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart("introduce", edit_profile_user_introduce.text.toString())
            imageMultipart.forEach {
                Log.d("TAG", "multi$it")
                builder.addFormDataPart(
                    "image",
                    imageName,
                    it
                )
            }

            val postBody: RequestBody = builder.build()
            myAPI = retrofit.create(Service::class.java)

            myAPI.updateUserData("Token ${Token.token}", postBody).enqueue(object : Callback<SuccessString>{
                override fun onResponse(
                    call: Call<SuccessString>,
                    response: Response<SuccessString>
                ) {
                    if(response.code() == 200)
                    {
                        getUserData()
                    }
                    Log.d("TAG", response.errorBody()?.string().toString())
                    Log.d("TAG", response.code().toString())
                    Log.d("TAG", response.message())
                }

                override fun onFailure(call: Call<SuccessString>, t: Throwable) {
                    Log.d("TAG", t.message.toString())
                }

            })
        }
    }

    fun getUserData()
    {
        myAPI.getUser("Token ${Token.token}")
            .enqueue(object : Callback<LoginInformation> {
                override fun onResponse(
                    call: Call<LoginInformation>,
                    response: Response<LoginInformation>
                ) {
                    Log.d("TAG", response.code().toString())
                    when (response.code()) {
                        200 -> {
                            LoginInformation.loginInfoData = response.body()!!
                            startActivity(MainActivity::class.java)
                            finish()
                        }
                    }
                }

                override fun onFailure(
                    call: Call<LoginInformation>,
                    t: Throwable
                ) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }
}