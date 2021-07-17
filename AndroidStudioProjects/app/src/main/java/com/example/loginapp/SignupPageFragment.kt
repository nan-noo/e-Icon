package com.example.loginapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loginapp.databinding.FragmentSignupBinding
import com.example.loginapp.model.LoginViewModel

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
        findNavController().navigate(R.id.action_signupPageFragment_to_startPageFragment)
    }

    fun cancel(){
        findNavController().navigate(R.id.action_signupPageFragment_to_startPageFragment)
    }
}