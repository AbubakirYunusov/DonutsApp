package com.example.donutsapp.presentation.main_screen

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentMainScreenBinding
import com.example.donutsapp.data.model.FoodModel
import com.example.donutsapp.data.preferences.FoodCartSharedPref
import com.example.donutsapp.presentation.adapter.FoodAdapter
import com.example.donutsapp.presentation.adapter.FoodsitemClickListener
import com.google.android.material.internal.ViewUtils.hideKeyboard

class MainScreenFragment : Fragment(), FoodsitemClickListener {

    private val binding: FragmentMainScreenBinding by lazy {
        FragmentMainScreenBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainScreenViewModel

    private val foodAdapter: FoodAdapter by lazy {
        FoodAdapter(this)
    }

    private val sharedPreferences: FoodCartSharedPref by lazy {
        FoodCartSharedPref(requireContext())
    }

    private var foodList: List<FoodModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView2.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_foodBasketFragment)
        }
        viewModel = ViewModelProvider(
            this,

            ViewModelProvider.NewInstanceFactory()
        )[MainScreenViewModel::class.java]
        setupStatusColors()
        setUpObserveData()
        setUpViews()
        setClik()
    }

    private fun setClik() {
        binding.foodSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let {
                    filterFood(it)
                }
                return true
            }
        })
    }

    private fun filterFood(title: String) {
        val filterFood = foodList.filter { name ->
            name.foodName.contains(title, ignoreCase = true)
        }
        foodAdapter.updateFoodList(filterFood)
    }

    private fun setupStatusColors() {
        requireActivity().window?.statusBarColor =
            resources.getColor(R.color.onbording_btn_beckraund)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }

    private fun setUpObserveData() = viewModel.apply {
        foodLiveData.observe(viewLifecycleOwner) { foods ->
            foodAdapter.updateFoodList(foods)
            foodList = foods
        }
    }

    private fun setUpViews() = binding.apply {
        recyclerView.adapter = foodAdapter
    }

    override fun onFoodItemClick(model: FoodModel) {
        findNavController().navigate(
            R.id.action_mainScreenFragment_to_foodDetailsFragment,
            bundleOf(FOOD_KYE to model)
        )
    }

    override fun onFoodItemBackClick(index: Int) {

    }

    companion object {
        const val FOOD_KYE = "FOOD_KYE"
    }
}