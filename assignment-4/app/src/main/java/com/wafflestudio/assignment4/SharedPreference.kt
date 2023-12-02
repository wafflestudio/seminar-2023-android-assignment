package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)

    fun fetchString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().apply {
            putString(key, value)
            apply()
        }
    }
}
