package com.example.donutsapp.presentation.adapter

import android.icu.text.AlphabeticIndex
import com.example.donutsapp.data.model.FoodModel

interface FoodsitemClickListener {
    fun onFoodItemClick(foodModel: FoodModel)
    fun onFoodItemBackClick(index: Int)

}