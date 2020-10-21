package com.example.hischool.view.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.module.RotateImage
import kotlinx.android.synthetic.main.fragment_sign_up_profile.view.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

class SignUpProfileFragment : Fragment() {

    private val profileImagePick = 100

    private val imageMultipart = ArrayList<RequestBody>()
    private val imageNameList = ArrayList<String>()
    private val rotateImageClass = RotateImage() //이미지 회전

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up_profile, container, false)

        view.profile.setOnClickListener {
            val imageIntent = Intent() //구글 갤러리 접근 intent 변수

            if (imageMultipart.size < 5) {
                //구글 갤러리 접근
                imageIntent.type = "image/*"
                imageIntent.action = Intent.ACTION_GET_CONTENT
                if(imageIntent.resolveActivity(requireActivity().packageManager) != null){
                    startActivityForResult(imageIntent, profileImagePick)
                }
            } else {
                Toast.makeText(activity?.applicationContext, "사진은 5개까지 올릴 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

    //갤러리에서 넘어온 이미지 처리
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val returnUri: Uri
        val returnCursor: Cursor?

        if(resultCode == RESULT_OK){
            when(requestCode){
                profileImagePick -> {

                    //이미지 정보
                    returnUri = data?.data!!//이미지 커서

                    //이미지 파일 받아오기
                    val inputStream =
                        activity?.contentResolver?.openInputStream(returnUri) //input 스트림
                    var bm: Bitmap = BitmapFactory.decodeStream(inputStream) //비트맵 변환
                    bm = rotateImageClass.rotateImage(data.data!!, bm, activity?.contentResolver!!) //이미지 회전
                    val bos = ByteArrayOutputStream()
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                    imageMultipart.add(RequestBody.create(MultipartBody.FORM, bos.toByteArray()))

                    inputStream?.close()

                    returnCursor = activity?.contentResolver?.query(returnUri, null, null, null, null)

                    //이미지 이름
                    val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    returnCursor.moveToFirst()
                    imageNameList.add(returnCursor.getString(nameIndex))

                    returnCursor.close()

                }

                else -> {
                    Toast.makeText(activity?.applicationContext, "이미지를 제대로 가져오지 못하였습니다.", Toast.LENGTH_LONG).show()
                }

            }
        }

    }
}