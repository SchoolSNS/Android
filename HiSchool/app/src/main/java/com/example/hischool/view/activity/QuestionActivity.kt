package com.example.hischool.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hischool.R
import com.example.hischool.widget.startActivity

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(MainActivity::class.java)
    }
}