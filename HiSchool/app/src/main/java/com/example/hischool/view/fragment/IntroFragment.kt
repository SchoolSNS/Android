package com.example.hischool.view.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hischool.R
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.LoginData
import com.example.hischool.room.LoginDataBase
import com.example.hischool.view.activity.MainActivity
import com.example.hischool.view.activity.SignActivity
import kotlinx.android.synthetic.main.fragment_intro.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class IntroFragment : Fragment() {

    //뒤로가기
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_intro, container, false)

        view.signInButton.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.signInFragment)
        }

        view.signUpButton.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.signUpFragment)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().moveTaskToBack(true)
                requireActivity().finishAndRemoveTask()
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

}