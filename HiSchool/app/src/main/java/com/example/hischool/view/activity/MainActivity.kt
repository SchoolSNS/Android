package com.example.hischool.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hischool.sharedpreferences.App.Companion.prefs
import com.example.hischool.R
import com.example.hischool.widget.noFinishStartActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //override fun onResume() {
        //Log.d("TAG", "RECEIVE : ${this.getPreferences(0).getString("school_name", "학교 이름")}")
        //school_name_text.text = this.getPreferences(0).getString("school_name", "학교 이름")
        //super.onResume()
    //}

    override fun onResume() {

        school_name_text.text =  prefs.schoolEditText
        super.onResume()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionbar = supportActionBar
        actionbar?.hide()

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)
        school_name_text.setOnClickListener {
            noFinishStartActivity(SelectSchoolActivity::class.java)
        }

        floatingButton.setOnClickListener {
            startActivity(Intent(this, ChattingRoomActivity::class.java))
        }
    }

}