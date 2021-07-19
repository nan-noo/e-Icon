package com.example.loginapp.server

import com.google.gson.annotations.SerializedName

data class UserInfo (
    @SerializedName("id")
    val id: String,

    @SerializedName("email")
    val email: String
    )