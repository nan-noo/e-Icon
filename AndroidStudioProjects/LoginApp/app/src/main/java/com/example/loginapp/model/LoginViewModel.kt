package com.example.loginapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    // userInput
    val userId = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()

    // from server
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    init {
        resetText()
    }

    fun setUserInfo(id: String, email: String){
        _id.value = id
        _email.value = email
    }

    fun resetText() {
        userId.value = ""
        userPassword.value = ""
        userEmail.value = ""
        _id.value = ""
        _email.value = ""
    }
}