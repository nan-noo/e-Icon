package com.example.loginapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.loginapp.databinding.FragmentStartBinding
import com.example.loginapp.model.LoginViewModel

class StartPageFragment: Fragment() {
    private val sharedViewModel: LoginViewModel by activityViewModels()

    // Binding object instance corresponding to the fragment_start.xml layout
    private var binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun goToLoginPage(){
        findNavController().navigate(R.id.action_startPageFragment_to_loginPageFragment)
    }

    fun goToSignupPage(){
        findNavController().navigate(R.id.action_startPageFragment_to_signupPageFragment)
    }
}