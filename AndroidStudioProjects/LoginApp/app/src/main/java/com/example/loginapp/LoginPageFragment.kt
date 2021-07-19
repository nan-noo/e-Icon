package com.example.loginapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loginapp.databinding.FragmentLoginBinding
import com.example.loginapp.model.LoginViewModel
import com.example.loginapp.server.RetrofitApi
import com.example.loginapp.server.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPageFragment: Fragment() {
    private val sharedViewModel: LoginViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_login.xml layout
    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            loginFragment = this@LoginPageFragment
        }
    }

    // check if the user already signed up
    // if not, Toast message "no user data"
    fun login(){
        val userId = sharedViewModel.userId.value.toString()
        val userPassword = sharedViewModel.userPassword.value.toString()
        val map = HashMap<String, String>()
        map["id"] = userId
        map["password"] = userPassword

        val result = RetrofitApi.retrofitService.executeLogin(map)
        result.enqueue(object: Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                Log.d("response : ", response.body().toString())

                if(response.code() == 200){ // login success
                    val userInfo = response.body()
                    if (userInfo != null) {
                        sharedViewModel.resetText()
                        sharedViewModel.setUserInfo(userInfo.id, userInfo.email)
                        findNavController().navigate(R.id.action_loginPageFragment_to_homePageFragment)
                    }
                }
                else if (response.code() == 404){ // login failed
                    Toast.makeText(context, "일치하는 정보가 없습니다.", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    fun cancel(){
        sharedViewModel.resetText()
        findNavController().navigate(R.id.action_loginPageFragment_to_startPageFragment)
    }
}