package com.example.hischool.data.login

data class LoginInformation(
    val id: String,
    val email: String,
    val userName: String,
    val profile: String,
    val description: String,
    val followers: String,
    val followings: String
) {
    companion object {
        lateinit var loginInfoData: LoginInformation
    }
}