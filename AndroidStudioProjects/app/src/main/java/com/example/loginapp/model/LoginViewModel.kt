package com.example.loginapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    // userInput
    val userId = MutableLiveData<String>("")
    val userPassword = MutableLiveData<String>("")

    // from server
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    init {
        resetText()
    }

    fun setId(userId: String) {
        _id.value = userId
    }

    fun setEmail(userEmail: String) {
        _email.value = userEmail
    }

    fun setPassword(userPassword: String) {
        _password.value = userPassword
    }

    fun setUserInfo(userId:String, userEmail: String, userPassword: String){
        _id.value = userId
        _email.value = userEmail
        _password.value = userPassword
    }

    private fun resetText() {
        userId.value = ""
        userPassword.value = ""
        _id.value = ""
        _email.value = ""
        _password.value = ""
    }
}