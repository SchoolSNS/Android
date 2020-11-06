package com.example.hischool.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.hischool.R
import com.example.hischool.view.activity.SignActivity
import kotlinx.android.synthetic.main.fragment_sign_up_name.view.*
import kotlinx.android.synthetic.main.fragment_sign_up_name.view.nextButton
import kotlinx.android.synthetic.main.fragment_sign_up_school.view.*

class SignUpSchoolFragment : Fragment() {

    //이름 형식이 맞는지 검사할 때 사용하는 변수들
    private var checkName: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up_school, container, false)

        //이름 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        view.schoolName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkName()
            }

            override fun afterTextChanged(s: Editable) {
                checkName()
            }
        })

        view.nextButton.setOnClickListener {
            (activity as SignActivity).school = view.schoolName.text.toString()
            val navController = view.findNavController()
            navController.navigate(R.id.signUpProfileFragment)
        }

        view.xButtonSchool.setOnClickListener {
            view.findNavController().navigate(R.id.introFragment)
        }

        return view
    }

    private fun checkName() {
        if (view?.name?.text.toString().isNotEmpty()) {
            checkName = true
            checkButton(checkName)
        } else {
            checkName = false
            checkButton(checkName)
        }
    }

    private fun checkButton(checkName: Boolean) {
        this.checkName = checkName

        if (checkName) {

            view?.nextButton?.setBackgroundResource(R.drawable.next_button_background)
            view?.nextButton?.isEnabled = true

        } else {

            view?.nextButton?.setBackgroundResource(R.drawable.next_button_background_false)
            view?.nextButton?.isEnabled = false

        }
    }

}