package com.example.hischool.module

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.hischool.R
import com.example.hischool.data.SignInResponse
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.LoginData
import com.example.hischool.room.LoginDataBase
import com.example.hischool.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//로그인 다이얼로그 클래스
class SignInDialog {

    //로그인 다이얼로그
    internal fun connectionSuccess(
        response: Response<SignInResponse>,
        context: Context,
        loginDataBase: LoginDataBase,
        sweetAlertDialog: SweetAlertDialog,
        intent: Intent,
        myAPI: Service,
        retrofit: Retrofit
    ) {
        //통신 성공
        when (response.code()) {
            200 -> {
                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("로그인이 완료 되었습니다")
                    .setConfirmClickListener {
                        loginDataBase.loginDao().insert(
                            LoginData(
                                0,
                                response.body()!!.token
                            )
                        )
                        Token.token = loginDataBase.loginDao().getAll()[0].token

                        if (loginDataBase.loginDao().getAll().isNotEmpty()) {

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

                                                startActivity(context, intent, null)
                                                (context as Activity).finish()
                                                ActivityCompat.finishAffinity(context)
                                                dialog.dismiss()
                                            }
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<LoginInformation>,
                                        t: Throwable
                                    ) {
                                    }
                                })
                        }
                    }
                    .show()

            }

            401 -> {
                sweetAlertDialog.dismiss()
                val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("서버 통신에 실패하였습니다.")
                    .setConfirmClickListener {
                        dialog.dismiss()
                    }
                    .setContentText(response.errorBody()?.string().toString())
                    .show()
            }

            500 -> {
                sweetAlertDialog.dismiss()
                val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("서버 통신에 실패하였습니다.")
                    .setConfirmClickListener {
                        dialog.dismiss()
                    }
                    .setContentText("관리자에게 문의하세요")
                    .show()
            }
        }
    }

    fun connectionFail(context: Context, sweetAlertDialog: SweetAlertDialog) {

        sweetAlertDialog.dismiss()
        val dialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)

        dialog.setCancelable(false)

        dialog.setTitleText("서버 통신에 실패하였습니다.")
            .setConfirmClickListener {
                dialog.dismiss()
            }
            .setContentText("네트워크 연결을 확인해 주세요.")
            .show()
    }

}