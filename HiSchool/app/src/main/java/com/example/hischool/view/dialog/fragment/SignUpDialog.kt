package com.example.hischool.view.dialog.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.hischool.R

class SignUpDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_sign_up, container, false)

        val bundle = arguments
        val value = bundle?.getString("key")

        val fragment = activity?.supportFragmentManager?.findFragmentByTag("tag")
        if(fragment != null){
            val dialogFragment: DialogFragment = fragment as DialogFragment
            dialogFragment.dismiss()
        }

        view.setOnClickListener{

        }

        return view
    }
}