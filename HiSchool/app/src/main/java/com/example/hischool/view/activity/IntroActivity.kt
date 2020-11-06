package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.hischool.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }
}