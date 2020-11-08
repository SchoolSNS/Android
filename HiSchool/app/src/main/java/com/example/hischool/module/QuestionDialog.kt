package com.example.hischool.module

import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.hischool.adapter.CommentImagePreViewAdapter
import com.example.hischool.data.PostResponse
import com.example.hischool.data.comment.WriteCommentResponse
import retrofit2.Response

class QuestionDialog {
    internal fun connectionSuccess(
        response: Response<PostResponse>,
        context: Context,
        sweetAlertDialog: SweetAlertDialog,
        intent: Intent
    ) {

        when (response.code()) {
            201 -> {
                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("게시물 작성이 완료 되었습니다")
                    .setConfirmClickListener {
                        ContextCompat.startActivity(context, intent, null)
                        dialog.dismiss()
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