package com.example.donutsapp.data.repository

import com.example.donutsapp.data.model.FoodModel

interface GetAllFoodsRepository {

    suspend fun getAllFoods(): List<FoodModel>

}