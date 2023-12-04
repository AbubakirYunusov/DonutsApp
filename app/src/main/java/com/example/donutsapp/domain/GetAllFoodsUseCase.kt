package com.example.donutsapp.domain

import com.example.donutsapp.data.model.FoodModel

interface GetAllFoodsUseCase {

   suspend fun getAllFoods(): List<FoodModel>

}