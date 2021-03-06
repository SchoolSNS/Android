package com.example.hischool.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hischool.R
import com.example.hischool.data.DeviceTokenBody
import com.example.hischool.data.SuccessResponse
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.LoginDataBase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class LoadingActivity : AppCompatActivity() {

    //database
    private lateinit var loginDataBase: LoginDataBase

    //retrofit
    private lateinit var myAPI: Service
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val actionbar = supportActionBar
        actionbar?.hide()

        retrofit = RetrofitClient.getInstance()


        loginDataBase = LoginDataBase.getInstance(applicationContext)!!

        if (loginDataBase.loginDao().getAll().isNotEmpty()) {

            myAPI = retrofit.create(Service::class.java)

            Token.token = loginDataBase.loginDao().getAll()[0].token

            myAPI.getUser("Token ${Token.token}")
                .enqueue(object : Callback<LoginInformation> {
                    override fun onResponse(
                        call: Call<LoginInformation>,
                        response: Response<LoginInformation>
                    ) {
                        when (response.code()) {
                            200 -> {
                                LoginInformation.loginInfoData = response.body()!!
                                Log.d("token", "token: ${Token.token}")
                                Log.d("token", "user: ${LoginInformation.loginInfoData}")
                                postDeviceToken()
                                startActivity(
                                    Intent(
                                        applicationContext,
                                        MainActivity::class.java
                                    )
                                )
                                finish()
                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginInformation>, t: Throwable) {
                        Toast.makeText(applicationContext, "네트워크 연결을 확인해주세요", Toast.LENGTH_SHORT)
                            .show()
                    }
                })

        } else {
            startActivity(Intent(applicationContext, SignActivity::class.java))
            finish()
        }
    }

    private fun postDeviceToken()
    {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.d("TAG", "getInstanceId failed${it.exception}")
                } else {
                    val token = it.result?.token
                    Log.d("TAG", "device : ${token}")
                    myAPI = retrofit.create(Service::class.java)
                    myAPI.refreshToken("Token ${Token.token}", DeviceTokenBody(token!!)).enqueue(object : Callback<SuccessResponse>{
                        override fun onResponse(
                            call: Call<SuccessResponse>,
                            response: Response<SuccessResponse>
                        ) {
                            Log.d("TAG" ,"rcode : ${response.code()}")
                        }

                        override fun onFailure(call: Call<SuccessResponse>, t: Throwable) {
                        }

                    })
                    Log.d("TAG", "내토큰 : $token")
                }
            }
    }

}
