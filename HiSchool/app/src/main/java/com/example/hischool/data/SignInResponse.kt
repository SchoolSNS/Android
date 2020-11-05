package com.example.hischool.data

data class SignInResponse(
    var token: String,
    var code : String,
    var message : String
)