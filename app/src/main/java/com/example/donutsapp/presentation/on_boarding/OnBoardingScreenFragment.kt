package com.example.donutsapp.presentation.on_boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentOnBoardingScreenBinding
import com.example.donutsapp.data.preferences.UserEnteredSharedPref

class OnBoardingScreenFragment : Fragment() {

    private val binding: FragmentOnBoardingScreenBinding by lazy {
        FragmentOnBoardingScreenBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: UserEnteredSharedPref by lazy {
        UserEnteredSharedPref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListener()
        checkFirstUser()
    }

    private fun setUpClickListener() = binding.apply {
        getStartedBtn.setOnClickListener {
            sharedPreferences.setUserFirstSign(true)
            goToMainScreen()
        }
    }

    private fun checkFirstUser() {
        if (sharedPreferences.getIsUserFirstSign()) goToMainScreen()
    }

    private fun goToMainScreen() =
        findNavController().navigate(R.id.action_onBoardingScreenFragment_to_mainScreenFragment)
}