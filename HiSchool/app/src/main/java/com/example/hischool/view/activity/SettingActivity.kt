package com.example.hischool.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.hischool.R
import com.example.hischool.data.login.LoginInformation
import com.example.hischool.room.LoginData
import com.example.hischool.room.LoginDataBase
import com.example.hischool.sharedpreferences.App
import com.example.hischool.widget.ThemeManager
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val themeMode = App.prefs.getTheme(true)

        back_constraint.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setting_switch.setOnCheckedChangeListener { compoundButton, onSwitch ->
            Log.d("TAG", onSwitch.toString())
            when (onSwitch) {
                true -> {
                    ThemeManager.applyTheme(ThemeManager.ThemeMode.DARK)
                    App.prefs.setTheme(true)
                }

                false -> {
                    ThemeManager.applyTheme(ThemeManager.ThemeMode.LIGHT)
                    App.prefs.setTheme(false)
                }
            }
        }

        Log.d("TAG", themeMode.toString())
        when (themeMode) {
            true -> {
                setting_switch.isChecked = true
            }
            false -> {
                setting_switch.isChecked = false
            }
        }

        setting_logout_btn.setOnClickListener {
            LoginDataBase.getInstance(applicationContext)!!.loginDao().delete()
            startActivity(Intent(this, SignActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}