package com.example.loginapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loginapp.databinding.FragmentLoginBinding
import com.example.loginapp.model.LoginViewModel

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
        sharedViewModel.checkUser()
        findNavController().navigate(R.id.action_loginPageFragment_to_homePageFragment)
        if(sharedViewModel.flag.value == true){
            findNavController().navigate(R.id.action_loginPageFragment_to_homePageFragment)
        }
        else{
            Toast.makeText(context, sharedViewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
        }

    }

    fun cancel(){
        sharedViewModel.resetText()
        findNavController().navigate(R.id.action_loginPageFragment_to_startPageFragment)
    }
}