package com.example.datausage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.datausage.databinding.FragmentMainBinding
import com.example.datausage.model.MainViewModel

class MainFragment: Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            mainFragment = this@MainFragment
        }
    }

    fun goToMap(){
        findNavController().navigate(R.id.action_mainFragment_to_mapFragment)
    }

    fun goToGuideline(){
        findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
    }
}