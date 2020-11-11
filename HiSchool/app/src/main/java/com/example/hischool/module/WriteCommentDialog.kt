package com.example.hischool.module

import android.content.Context
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.hischool.adapter.CommentImagePreViewAdapter
import com.example.hischool.data.comment.WriteCommentResponse
import com.example.hischool.view.activity.CommentActivity
import kotlinx.android.synthetic.main.activity_comment.*
import retrofit2.Callback
import retrofit2.Response

class WriteCommentDialog {

    internal fun connectionSuccess(
        response: Response<WriteCommentResponse>,
        context: Context,
        sweetAlertDialog: SweetAlertDialog,
        commentImagePreViewAdapter: CommentImagePreViewAdapter
    ) {

        when (response.code()) {
            201 -> {
                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("댓글 작성이 완료 되었습니다")
                    .setConfirmClickListener {
                        commentImagePreViewAdapter.notifyDataSetChanged()
                        dialog.dismiss()
                    }
                    .show()

                val commentActivity = CommentActivity()
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