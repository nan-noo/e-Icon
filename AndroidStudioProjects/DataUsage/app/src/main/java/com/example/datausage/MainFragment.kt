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
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet

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
        onChartClick()
    }

    fun onChartClick(){
        sharedViewModel.setChartData()

        val barChart = binding?.pieChart
        barChart?.description?.isEnabled = false;

        val dataSet = PieDataSet(sharedViewModel.entries, "")
        dataSet.colors = sharedViewModel.colors

        val data = PieData(dataSet)
        data.setValueTextSize(11f)

        barChart?.data = data
        barChart?.animateY(1400, Easing.EaseInOutQuad)
        barChart?.animate()
    }

    fun goToMap(){
        findNavController().navigate(R.id.action_mainFragment_to_mapFragment)
    }

    fun goToGuideline(){
        findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
    }
}