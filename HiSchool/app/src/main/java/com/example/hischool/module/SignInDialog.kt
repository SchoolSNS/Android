package com.example.hischool.module

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.hischool.R
import com.example.hischool.data.SignInResponse
import com.example.hischool.room.LoginData
import com.example.hischool.room.LoginDataBase
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import retrofit2.Response

class SignInDialog {

    internal fun connectionSuccess(
        response: Response<SignInResponse>,
        context: Context,
        loginDataBase: LoginDataBase,
        sweetAlertDialog: SweetAlertDialog,
        intent: Intent
    ) {
        //통신 성공
        when (response.code()) {
            200 -> {
                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("로그인이 완료 되었습니다")
                    .setConfirmClickListener {
                        ContextCompat.startActivity(context, intent, null)
                        (context as Activity).finish()
                        ActivityCompat.finishAffinity(context)
                        dialog.dismiss()
                    }
                    .show()

                Log.d("tokenData", "data: ${response.body()!!.token}")

                /*loginDataBase.loginDao().insert(
                    LoginData(
                        0,
                        response.body()!!.token
                    )
                )*/

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