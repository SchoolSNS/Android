package com.example.hischool.view.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.hischool.R
import com.example.hischool.data.DeviceTokenBody
import com.example.hischool.data.SignUpResponse
import com.example.hischool.data.SuccessResponse
import com.example.hischool.data.login.Token
import com.example.hischool.module.RotateImage
import com.example.hischool.module.SignUpDialog
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.view.activity.SignActivity
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.fragment_sign_up_profile.view.*
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

class SignUpProfileFragment : Fragment() {

    private val profileImagePick = 100

    private lateinit var myAPI: Service
    private lateinit var retrofit: Retrofit
    private lateinit var imageMultipart: RequestBody
    private var imageBoolean = false
    private var imageName = ""
    private val rotateImageClass = RotateImage() //이미지 회전

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = RetrofitClient.getInstance()
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up_profile, container, false)

        view.profile.setOnClickListener {
            val imageIntent = Intent() //구글 갤러리 접근 intent 변수

            //구글 갤러리 접근
            imageIntent.type = "image/*"
            imageIntent.action = Intent.ACTION_GET_CONTENT
            if (imageIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivityForResult(imageIntent, profileImagePick)
            }

        }

        view.signUpButton.setOnClickListener {
            myAPI = retrofit.create(Service::class.java)

            //로딩 다이얼로그 실행
            val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
            sweetAlertDialog
                .setTitleText("로딩 중")
                .setCancelable(false)
            sweetAlertDialog.show()

            val signUpDialog = SignUpDialog()

            //서버에 보낼 데이터
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

            builder.addFormDataPart("email", (activity as SignActivity).email)
            builder.addFormDataPart("username", (activity as SignActivity).username)
            builder.addFormDataPart("password", (activity as SignActivity).password)
            builder.addFormDataPart("identity", (activity as SignActivity).identity)
            builder.addFormDataPart("school", (activity as SignActivity).school)
            builder.addFormDataPart("introduce", view.introduce.text.toString())

            if (!imageBoolean) {
                var drawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_profile_image)
                drawable = (DrawableCompat.wrap(drawable!!)).mutate()
                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val bos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)

                builder.addFormDataPart(
                    "image",
                    "user",
                    RequestBody.create(MultipartBody.FORM, bos.toByteArray())
                )
            } else {
                builder.addFormDataPart("image", imageName, imageMultipart)
            }

            val signUpBody: RequestBody = builder.build()

            CoroutineScope(Dispatchers.IO).launch {
                myAPI.signUp(signUpBody)
                    .enqueue(object : Callback<SignUpResponse> {
                        override fun onResponse(
                            call: Call<SignUpResponse>,
                            response: Response<SignUpResponse>
                        ) {
                            signUpDialog.connectionSuccess(
                                response,
                                requireContext(),
                                view.findNavController(),
                                sweetAlertDialog
                            )
                        }

                        override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                            signUpDialog.connectionFail(requireContext(), sweetAlertDialog)
                            Log.d("throwable", "data: ${t.message}")
                        }
                    })
            }
        }

        view.xButton.setOnClickListener {
            view.findNavController().navigate(R.id.introFragment)
        }

        return view
    }

    //갤러리에서 넘어온 이미지 처리
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val returnUri: Uri
        val returnCursor: Cursor?

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                profileImagePick -> {
                    try {

                        //이미지 정보
                        returnUri = data?.data!!//이미지 커서

                        imageBoolean = true

                        //이미지 파일 받아오기
                        val inputStream =
                            activity?.contentResolver?.openInputStream(returnUri) //input 스트림
                        var bm: Bitmap = BitmapFactory.decodeStream(inputStream) //비트맵 변환
                        bm = rotateImageClass.rotateImage(
                            data.data!!,
                            bm,
                            activity?.contentResolver!!
                        ) //이미지 회전
                        val bos = ByteArrayOutputStream()
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                        imageMultipart = RequestBody.create(MultipartBody.FORM, bos.toByteArray())
                        inputStream?.close()

                        Glide.with(requireContext())
                            .load(bm)
                            .override(1000)
                            .transform(CenterCrop(), RoundedCorners(1000000000))
                            .into(view?.profile!!)

                        returnCursor =
                            activity?.contentResolver?.query(returnUri, null, null, null, null)

                        //이미지 이름
                        val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        returnCursor.moveToFirst()
                        imageName = returnCursor.getString(nameIndex)

                        returnCursor.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

                else -> {
                    Toast.makeText(
                        activity?.applicationContext,
                        "이미지를 제대로 가져오지 못하였습니다.",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

    }

    //권한 허용 확인
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {

            val length = permissions.size

            for (i in 0..length) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity", "권환 허용" + permissions[i])
                }
            }
        }
    }

    fun postToken()
    {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.d("TAG", "getInstanceId failed${it.exception}")
                } else {
                    val token = it.result?.token
                    myAPI = retrofit.create(Service::class.java)
                    myAPI.postToken("Token ${Token.token}", DeviceTokenBody(token!!)).enqueue(object : Callback<SuccessResponse>{
                        override fun onResponse(
                            call: Call<SuccessResponse>,
                            response: Response<SuccessResponse>
                        ) {

                        }

                        override fun onFailure(call: Call<SuccessResponse>, t: Throwable) {
                        }

                    })
                }
            }

    }
}