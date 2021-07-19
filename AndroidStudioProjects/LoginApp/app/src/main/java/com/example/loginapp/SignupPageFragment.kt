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
import com.example.loginapp.databinding.FragmentSignupBinding
import com.example.loginapp.model.LoginViewModel
import com.example.loginapp.server.RetrofitApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupPageFragment: Fragment() {
    private val sharedViewModel: LoginViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_signup.xml layout
    private var binding: FragmentSignupBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSignupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner // LifecycleOwner는 활동이나 프래그먼트와 같이 Android 수명 주기를 보유한 클래스
            viewModel = sharedViewModel
            signupFragment = this@SignupPageFragment
        }
    }

    // POST user inputs(id, e-mail, password) to server
    // 이미 가입한 유저면 Toast message "already signed up"
    // 가입하지 않은 유저면 data 서버에 전송
    fun signup(){
        val userId = sharedViewModel.userId.value.toString()
        val userEmail = sharedViewModel.userEmail.value.toString()
        val userPassword = sharedViewModel.userPassword.value.toString()
        val map = HashMap<String, String>()
        map["id"] = userId
        map["email"] = userEmail
        map["password"] = userPassword
        val result = RetrofitApi.retrofitService.executeSignup(map)
        result.enqueue(object: Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.d("response : ", response.code().toString())

                if(response.code() == 200){ // signup success
                    Toast.makeText(context, "등록되었습니다.", Toast.LENGTH_SHORT).show()
                    sharedViewModel.resetText()
                    findNavController().navigate(R.id.action_signupPageFragment_to_startPageFragment)
                }
                else if(response.code() == 400){ // already registered
                    Toast.makeText(context, "이미 등록된 사용자입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun cancel(){
        sharedViewModel.resetText()
        findNavController().navigate(R.id.action_signupPageFragment_to_startPageFragment)
    }
}