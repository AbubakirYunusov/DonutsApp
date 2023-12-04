package com.example.donutsapp.data.model

import java.io.Serializable

data class FoodModel(
    val foodName: String,
    val foodDescription: String,
    val foodPrice: Int,
    val foodImg: String,
) : Serializable