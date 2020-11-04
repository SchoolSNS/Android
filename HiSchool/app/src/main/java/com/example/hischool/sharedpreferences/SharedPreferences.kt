package com.example.hischool.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_SCHOOL_EDITTEXT = "schoolEditText"
    val prefs: SharedPreferences? = context.getSharedPreferences(PREFS_FILENAME, 0)

    var schoolEditText: String
        get() = prefs?.getString(PREF_KEY_SCHOOL_EDITTEXT,"학교 이름").toString()
        set(value) = prefs?.edit()?.putString(PREF_KEY_SCHOOL_EDITTEXT,value)!!.apply()
}