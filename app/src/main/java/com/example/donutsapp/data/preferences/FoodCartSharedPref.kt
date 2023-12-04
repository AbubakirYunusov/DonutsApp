package com.example.donutsapp.data.preferences

import android.content.Context
import com.example.donutsapp.data.model.FoodModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val FOOD_SHARED_PREFERENCES_KEY = "FOOD_SHARED_PREFERENCES_KYE"
private const val FOOD_PREF_KEY = "FOOD_PREF_KEY"

class FoodCartSharedPref(
    private val context: Context,
) {
    private val sharedPreferences = context.getSharedPreferences(
        FOOD_SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun saveFood(foodModel: FoodModel) {
        val food = getAllSavedFoods().toMutableList()
        food.add(0, foodModel)
        val foodGson = Gson().toJson(food)
        sharedPreferences
            .edit()
            .putString(FOOD_PREF_KEY, foodGson)
            .apply()
    }

    fun getAllSavedFoods(): List<FoodModel> {
        val json = sharedPreferences.getString(FOOD_PREF_KEY, null)
        val type: Type = object : TypeToken<List<FoodModel?>?>() {}.type
        val foodList = gson.fromJson<List<FoodModel>>(json, type)
        return foodList ?: emptyList()
    }

    fun deleteAllFood() = sharedPreferences.edit().clear().apply()

    fun deleteFoodAtIndex(index: Int) {
        val getAllCars = getAllSavedFoods().toMutableList()
        if (index in 0 until getAllCars.size) {
            getAllCars.removeAt(index)
            val carGsonString = Gson().toJson(getAllCars)
            sharedPreferences.edit().putString(FOOD_PREF_KEY, carGsonString).apply()
        }
    }
}