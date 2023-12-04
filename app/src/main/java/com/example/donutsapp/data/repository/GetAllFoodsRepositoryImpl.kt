package com.example.donutsapp.data.repository

import com.example.donutsapp.data.fake_data.foodFakeDates
import com.example.donutsapp.data.model.FoodModel

class GetAllFoodsRepositoryImpl : GetAllFoodsRepository {

    override suspend fun getAllFoods(): List<FoodModel> = foodFakeDates()

}