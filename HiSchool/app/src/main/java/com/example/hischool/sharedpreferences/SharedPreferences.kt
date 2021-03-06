package com.example.hischool.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    val PREFS_FILENAME = "prefs"
    val PREF_KEY_SCHOOL_EDITTEXT = "schoolEditText"
    val prefs: SharedPreferences? = context.getSharedPreferences(PREFS_FILENAME, 0)

    var schoolEditText: String
        get() = prefs?.getString(PREF_KEY_SCHOOL_EDITTEXT,"관심있는 학교를 선택해 주세요").toString()
        set(value) = prefs?.edit()?.putString(PREF_KEY_SCHOOL_EDITTEXT,value)!!.apply()

    fun getLastTime(defValue: Long): Long {
        return prefs!!.getLong("lastConnectTime", defValue)
    }

    fun setLastTime(long: Long?) {
        prefs!!.edit().putLong("lastConnectTime", long!!).apply()
    }

    fun getEmail(defValue: String): String {
        return prefs!!.getString("targetEmail", defValue).toString()
    }

    fun setEmail(string: String) {
        prefs!!.edit().putString("targetEmail", string).apply()
    }

    fun setTheme(bol: Boolean) {
        prefs!!.edit().putBoolean("permission", bol).apply()
    }

    fun getTheme(defValue: Boolean): Boolean {
        return prefs!!.getBoolean("permission", defValue)
    }
}