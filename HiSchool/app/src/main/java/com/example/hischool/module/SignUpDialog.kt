package com.example.hischool.module

import android.content.Context
import androidx.navigation.NavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.hischool.R
import com.example.hischool.data.SignUpResponse
import com.example.hischool.view.fragment.SignUpFragment
import com.example.hischool.view.fragment.SignUpProfileFragment
import retrofit2.Response

class SignUpDialog {

    internal fun connectionSuccess(
        response: Response<SignUpResponse>,
        context: Context,
        navController: NavController,
        sweetAlertDialog: SweetAlertDialog
    ) {
        //통신 성공
        when (response.code()) {
            201 -> {
                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("회원가입이 완료 되었습니다")
                    .setConfirmClickListener {
                        navController.navigate(R.id.introFragment)
                        dialog.dismiss()
                    }
                    .show()

            }

            400 -> {
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