package com.example.donutsapp.data.preferences

import android.content.Context

private const val SHARED_PREFERENCES_KEY = "SHARED_PREFERENCES_KEY"
private const val USER_SIGN_PREFERENCES_KEY = "USER_SIGN_PREFERENCES_KEY"

class UserEnteredSharedPref(
    context: Context,
) {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE
    )

    fun getIsUserFirstSign(): Boolean {
        return sharedPreferences.getBoolean(USER_SIGN_PREFERENCES_KEY, false)
    }

    fun setUserFirstSign(userFirstSign: Boolean) {
        sharedPreferences.edit().putBoolean(USER_SIGN_PREFERENCES_KEY, userFirstSign).apply()
    }
}