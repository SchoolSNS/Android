package com.example.hischool.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hischool.R
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.data.login.Token
import com.example.hischool.network.retrofit.RetrofitClient
import com.example.hischool.network.retrofit.Service
import com.example.hischool.room.LoginDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignActivity : AppCompatActivity() {

    internal var email: String = ""
    internal var username: String = ""
    internal var password: String = ""
    internal var identity: String = ""
    internal var school: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val actionbar = supportActionBar
        actionbar?.hide()

        checkSelfPermission()
    }

    private fun checkSelfPermission() {

        var temp = ""

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " "
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " "
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " "
        }

        if (!TextUtils.isEmpty(temp)) {
            ActivityCompat.requestPermissions(this, temp.trim().split(" ").toTypedArray(), 1)
        }

    }
}