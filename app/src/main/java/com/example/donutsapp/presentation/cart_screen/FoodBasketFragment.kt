package com.example.donutsapp.presentation.cart_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.navigation.fragment.findNavController
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentFoodBasketBinding
import com.example.donutsapp.data.model.FoodModel
import com.example.donutsapp.data.preferences.FoodCartSharedPref
import com.example.donutsapp.presentation.adapter.FoodAdapter
import com.example.donutsapp.presentation.adapter.FoodsitemClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class FoodBasketFragment : Fragment(), FoodsitemClickListener {

    private val binding: FragmentFoodBasketBinding by lazy {
        FragmentFoodBasketBinding.inflate(layoutInflater)
    }

    private val sharedPreferences: FoodCartSharedPref by lazy {
        FoodCartSharedPref(requireContext())
    }

    private val adapter: FoodAdapter by lazy {
        FoodAdapter(this)
    }

    private var foodList: List<FoodModel> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewsAndAdapter()
        setupClickListeners()
        setupStatusColors()
        setupViews()
    }

    private fun setupViews() {
        val savedFoodList = sharedPreferences.getAllSavedFoods()
        adapter.updateFoodList(savedFoodList)
        binding.mainRv.adapter = adapter
    }

    override fun onFoodItemClick(foodModel: FoodModel) {
    }

    override fun onFoodItemBackClick(index: Int) {
        sharedPreferences.deleteFoodAtIndex(index)
        setupViewsAndAdapter()
    }

    private fun setupClickListeners() {
        binding.ivBack1.setOnClickListener {
            findNavController().navigate(R.id.action_foodBasketFragment_to_mainScreenFragment)
        }
        binding.iconbasket.setOnClickListener {
            showConfirmDeleteFoodDialog()
        }
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
        val filterNote = foodList.filter { name ->
            name.foodName.contains(title, ignoreCase = true)
        }
        adapter.updateFoodList(filterNote)
    }


    private fun showConfirmDeleteFoodDialog() {
        if (binding.mainRv.isNotEmpty()) {

            val alertDialog = MaterialAlertDialogBuilder(requireContext())
            alertDialog.setMessage(resources.getString(R.string.text_do_all))
            alertDialog.setPositiveButton(resources.getString(R.string.text_yes)) { dialog, _ ->
                deleteAllSavedNotes()
                dialog.dismiss()
            }
            alertDialog.setNegativeButton(resources.getString(R.string.text_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            alertDialog.create().show()
        }
    }

    private fun setupViewsAndAdapter() {
        val listOfNotes = sharedPreferences.getAllSavedFoods()
        foodList = listOfNotes.toMutableList()
        adapter.updateFoodList(listOfNotes)
        binding.mainRv.adapter = adapter
        if (listOfNotes.isNotEmpty()) {
            binding.apply {
                mainRv.visibility = View.VISIBLE
            }
        } else {
            binding.mainRv.visibility = View.GONE
        }
    }

    private fun deleteAllSavedNotes() {
        sharedPreferences.deleteAllFood()
        adapter.updateFoodList(emptyList())
        binding.mainRv.visibility = View.GONE
    }

    private fun showToastManager(massage: String) {
        Snackbar.make(
            binding.root,
            massage,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    private fun setupStatusColors() {
        requireActivity().window?.statusBarColor =
            resources.getColor(R.color.white)
        requireActivity().window?.navigationBarColor = resources.getColor(R.color.white)
    }
}