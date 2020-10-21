package com.example.hischool.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.view.activity.SignActivity
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.email
import kotlinx.android.synthetic.main.fragment_sign_up.view.password
import kotlinx.android.synthetic.main.fragment_sign_up.view.passwordConfirm
import java.util.regex.Matcher

class SignUpFragment : Fragment() {

    //이메일 형식과 비밀번호 형식이 맞는지 검사할 때 사용하는 변수들
    private var checkEmail: Boolean = false
    private var checkPassword: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)

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

        //비밀번호 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        view.passwordConfirm.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkPassword()
            }

            override fun afterTextChanged(s: Editable) {
                checkPassword()
            }
        })

        view.nextButton.setOnClickListener {
            (activity as SignActivity).replaceFragment(SignUpNameFragment())
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
        if (view?.password?.text.toString()
                .isNotEmpty() && view?.password?.text.toString().length >= 6
            && view?.password?.text.toString() == view?.passwordConfirm?.text.toString()
        ) {
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

            view?.nextButton?.setBackgroundResource(R.drawable.next_button_background)
            view?.nextButton?.isEnabled = true

        } else {

            view?.nextButton?.setBackgroundResource(R.drawable.next_button_background_false)
            view?.nextButton?.isEnabled = false

        }
    }

}