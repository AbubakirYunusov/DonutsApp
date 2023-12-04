package com.example.donutsapp.presentation.details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentFoodDetailsBinding
import com.example.donutsapp.data.model.FoodModel
import com.example.donutsapp.data.preferences.FoodCartSharedPref
import com.example.donutsapp.presentation.main_screen.MainScreenFragment.Companion.FOOD_KYE
import com.google.android.material.snackbar.Snackbar

class FoodDetailsFragment : Fragment() {

    private val binding: FragmentFoodDetailsBinding by lazy {
        FragmentFoodDetailsBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: FoodCartSharedPref by lazy {
        FoodCartSharedPref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackPressedDispatcher()
        setupStatusColors()
        setupClickListeners()
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
        val foodModel = arguments?.getSerializable(FOOD_KYE) as? FoodModel
        foodModel?.let {
            setupViews(it)
            setupClickListeners(foodModel)
        }
    }

    private fun setupViews(foodModel: FoodModel) {
        binding.apply {
            Glide.with(requireContext()).load(foodModel.foodImg).into(ivFood)
            tvFoodTitle.text = foodModel.foodName
            tvFoodDescription.text = foodModel.foodDescription
            tvFoodPrice.text = "${foodModel.foodPrice}$"
        }
    }

    private fun setupClickListeners(foodModel: FoodModel) {
        binding.button.setOnClickListener {
            sharedPreferences.saveFood(foodModel)
            Snackbar.make(
                requireView(), "Added To Cart",
                Snackbar.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
    }

    private fun setupBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    private fun setupStatusColors() {
        requireActivity().window?.statusBarColor =
            resources.getColor(R.color.white)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }

    private fun setupClickListeners() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_foodDetailsFragment_to_mainScreenFragment)
        }
    }
}