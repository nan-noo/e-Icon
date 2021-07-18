package com.example.loginapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginapp.server.RetrofitApi
import com.example.loginapp.server.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    //
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _flag = MutableLiveData<Boolean>()
    val flag: LiveData<Boolean> = _flag


    init {
        resetText()
    }

    fun checkUser(){
        val result = RetrofitApi.retrofitService.executeLogin(userId.value, userPassword.value)
        result.enqueue(object: Callback<UserInfo>{
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                _flag.value = false
                _errorMessage.value = t.message
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.d("response : ", response.body().toString())

                if(response.code() == 200){
                    // login success
                    _flag.value = true
                    _errorMessage.value = ""
                    val userInfo = response.body()
                    if (userInfo != null) {
                        _id.value = userInfo.id
                        _email.value = userInfo.email
                    }
                }
                else if (response.code() == 404){
                    // login failed
                    _flag.value = false
                    _errorMessage.value = "일치하는 정보가 없습니다."
                }

            }
            })
    }

    fun registerUser(){
        val result = RetrofitApi.retrofitService.executeSignup(userId.value, userEmail.value, userPassword.value)
        result.enqueue(object: Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                _flag.value = false
                _errorMessage.value = t.message
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("response : ", response.body().toString())

                if(response.code() == 200){
                    // signup success
                    _flag.value = true
                    _errorMessage.value = "등록되었습니다."
                }
                else if(response.code() == 400){
                    // already registered
                    _flag.value = false
                    _errorMessage.value = "이미 등록된 사용자입니다."
                }

            }
        })
    }

    fun resetText() {
        userId.value = ""
        userPassword.value = ""
        _id.value = ""
        _email.value = ""
    }
}