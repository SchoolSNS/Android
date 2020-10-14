package com.example.hischool.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.view.activity.MainActivity
import com.example.hischool.view.activity.SignActivity
import kotlinx.android.synthetic.main.fragment_intro.view.*


class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_intro, container, false)

        view.signInButton.setOnClickListener {
            (activity as SignActivity).replaceFragment(SignInFragment())
        }

        view.signUpButton.setOnClickListener {
            (activity as SignActivity).replaceFragment(SignUpFragment())
        }

        return view
    }
}