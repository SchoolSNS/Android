package com.example.hischool.data.login

data class LoginInformation(
    val id: String,
    val email: String,
    val username: String,
    val profile: String,
    val identity: String,
    val school: String
) {
    companion object {
        lateinit var loginInfoData: LoginInformation
    }
}