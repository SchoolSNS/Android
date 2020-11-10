package com.example.hischool.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.hischool.R
import com.example.hischool.data.SignInResponse
import com.example.hischool.module.SignInDialog
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.LoginDataBase
import com.example.hischool.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import kotlinx.android.synthetic.main.fragment_sign_in.view.email
import kotlinx.android.synthetic.main.fragment_sign_in.view.password
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Matcher

class SignInFragment : Fragment() {

    //이메일 형식과 비밀번호 형식이 맞는지 검사할 때 사용하는 변수들
    private var checkEmail: Boolean = false
    private var checkPassword: Boolean = false

    //retrofit
    private lateinit var myAPI: Service
    private lateinit var retrofit: Retrofit

    //database
    private lateinit var loginDataBase: LoginDataBase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = RetrofitClient.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_in, container, false)

        loginDataBase = LoginDataBase.getInstance(requireActivity().applicationContext)!!

        //이메일 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        view.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkEmail()
            }

            override fun afterTextChanged(s: Editable) {
                checkEmail()
            }
        })

        //비밀번호 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        view.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkPassword()
            }

            override fun afterTextChanged(s: Editable) {
                checkPassword()
            }
        })

        view.loginButton.setOnClickListener {
            myAPI = retrofit.create(Service::class.java)

            //로딩 다이얼로그 실행
            val sweetAlertDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
            sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
            sweetAlertDialog
                .setTitleText("로딩 중")
                .setCancelable(false)
            sweetAlertDialog.show()

            val signInDialog = SignInDialog()

            //서버에 보낼 데이터
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

            builder.addFormDataPart("email", view.email.text.toString())
            builder.addFormDataPart("password", view.password.text.toString())

            val signInBody: RequestBody = builder.build()

            CoroutineScope(Dispatchers.IO).launch {
                myAPI.signIn(signInBody)
                    .enqueue(object : Callback<SignInResponse> {
                        override fun onResponse(
                            call: Call<SignInResponse>,
                            response: Response<SignInResponse>
                        ) {
                            signInDialog.connectionSuccess(
                                response,
                                requireContext(),
                                loginDataBase,
                                sweetAlertDialog,
                                Intent(requireContext(), MainActivity::class.java),
                                myAPI, retrofit
                            )
                        }

                        override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                            signInDialog.connectionFail(requireContext(), sweetAlertDialog)
                            Log.d("throwable", "data: ${t.message}")
                        }
                    })
            }
        }

        return view
    }

    private fun checkEmail() {
        if (isEmail(view?.email?.text.toString())) {
            checkEmail = true
            checkButton(checkEmail, checkPassword)
        } else {
            checkEmail = false
            checkButton(checkEmail, checkPassword)
        }
    }

    private fun checkPassword() {
        if (view?.password?.text.toString().isNotEmpty()) {
            checkPassword = true
            checkButton(checkEmail, checkPassword)
        } else {
            checkPassword = false
            checkButton(checkEmail, checkPassword)
        }
    }

    //이메일 형식 체크
    private fun isEmail(email: String): Boolean {
        var returnValue = false
        val pattern = Patterns.EMAIL_ADDRESS
        val m: Matcher = pattern.matcher(email)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }

    private fun checkButton(checkEmail: Boolean, checkPassword: Boolean) {

        this.checkEmail = checkEmail
        this.checkPassword = checkPassword

        Log.d("data1", "data: $checkEmail")
        Log.d("data1", "data: $checkPassword")

        if (checkEmail && checkPassword) {

            view?.loginButton?.setBackgroundResource(R.drawable.next_button_background)
            view?.loginButton?.isEnabled = true

        } else {

            view?.loginButton?.setBackgroundResource(R.drawable.next_button_background_false)
            view?.loginButton?.isEnabled = false

        }
    }
}