package com.example.donutsapp.presentation.main_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentMainScreenBinding
import com.example.donutsapp.presentation.adapter.FoodAdapter

class MainScreenFragment : Fragment() {

    private val binding: FragmentMainScreenBinding by lazy {
        FragmentMainScreenBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainScreenViewModel

    private val foodAdapter: FoodAdapter by lazy {
        FoodAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainScreenViewModel::class.java]
        setupStatusColors()
        setUpObserveData()
        setUpViews()
    }

    private fun setupStatusColors() {
        requireActivity().window?.statusBarColor =
            resources.getColor(R.color.onbording_btn_beckraund)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }

    private fun setUpObserveData() = viewModel.apply {
        foodLiveData.observe(viewLifecycleOwner) { foodList ->
            foodAdapter.updateFoodList(foodList)
        }
    }

    private fun setUpViews() = binding.apply {
        recyclerView.adapter = foodAdapter
    }

}