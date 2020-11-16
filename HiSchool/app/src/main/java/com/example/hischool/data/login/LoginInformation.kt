package com.example.hischool.data.login

data class LoginInformation(
    val id: Int,
    val email: String,
    val username: String,
    var profile: String,
    val identity: String,
    val school: String,
    val introduce: String
) {
    companion object {
        lateinit var loginInfoData: LoginInformation
    }
}