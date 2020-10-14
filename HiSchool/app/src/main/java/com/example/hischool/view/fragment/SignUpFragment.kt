package com.example.hischool.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.hischool.R
import com.example.hischool.view.dialog.fragment.SignUpDialog

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val args = Bundle()
        args.putString("key", "value")

        val dialog = SignUpDialog()
        dialog.arguments = args
        dialog.show(activity?.supportFragmentManager!!, "tag")

        return view
    }
}