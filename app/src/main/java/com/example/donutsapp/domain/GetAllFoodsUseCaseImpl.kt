package com.example.donutsapp.domain

import com.example.donutsapp.data.model.FoodModel
import com.example.donutsapp.data.repository.GetAllFoodsRepositoryImpl

class GetAllFoodsUseCaseImpl : GetAllFoodsUseCase {

    private val foodsRepository = GetAllFoodsRepositoryImpl()

    override suspend fun getAllFoods(): List<FoodModel> {
        return foodsRepository.getAllFoods()
    }

}