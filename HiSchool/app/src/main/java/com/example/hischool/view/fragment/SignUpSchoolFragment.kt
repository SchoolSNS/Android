package com.example.hischool.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hischool.R
import com.example.hischool.view.activity.SignActivity
import kotlinx.android.synthetic.main.fragment_sign_up_name.view.*
import kotlinx.android.synthetic.main.fragment_sign_up_name.view.nextButton

class SignUpNameFragment : Fragment() {

    //이름 형식이 맞는지 검사할 때 사용하는 변수들
    private var checkName: Boolean = false
    private var checkIdentity: Boolean = false

    //사용자의 역할을 저장하는 변수
    private var identity = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up_name, container, false)

        //spinner item
        val items = resources.getStringArray(R.array.spinnerArray)
        val adapter = ArrayAdapter(requireActivity().applicationContext, android.R.layout.simple_spinner_dropdown_item, items)
        view.spinner.adapter = adapter

        view.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when(position){

                    0 ->{
                        checkIdentity = false
                        checkName()
                    }

                    1 -> {
                        checkIdentity = true
                        identity = "후배"
                        checkName()
                    }

                    2 -> {
                        checkIdentity = true
                        identity = "선배"
                        checkName()
                    }

                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                view.spinner.setSelection(0)
            }
        }

        //이름 형식이 맞으면 버튼 클릭이 가능 하도록 하는 코드
        view.name.addTextChangedListener(object : TextWatcher {
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
            (activity as SignActivity).username = view.name.text.toString()
            (activity as SignActivity).identity = identity
            val navController = view.findNavController()
            navController.navigate(R.id.signS)
        }

        return view
    }

    private fun checkName() {
        if (view?.name?.text.toString().isNotEmpty()) {
            checkName = true
            checkButton(checkName, checkIdentity)
        } else {
            checkName = false
            checkButton(checkName, checkIdentity)
        }
    }

    private fun checkButton(checkName: Boolean, checkIdentity: Boolean) {
        this.checkName = checkName
        this.checkIdentity = checkIdentity

        if (checkName && checkIdentity) {

            view?.nextButton?.setBackgroundResource(R.drawable.next_button_background)
            view?.nextButton?.isEnabled = true

        } else {

            view?.nextButton?.setBackgroundResource(R.drawable.next_button_background_false)
            view?.nextButton?.isEnabled = false

        }
    }

}