package com.example.donutsapp.presentation.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.donutsapp.data.model.FoodModel
import com.example.donutsapp.domain.GetAllFoodsUseCaseImpl
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val getAllFoodsUseCase = GetAllFoodsUseCaseImpl()

    val foodLiveData: MutableLiveData<List<FoodModel>> = MutableLiveData()

    init {
        getAllFoods()
    }

    private fun getAllFoods() = viewModelScope.launch {
        val response = getAllFoodsUseCase.getAllFoods()
        foodLiveData.postValue(response)
    }

}