package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences (context: Context){
    private val preferences: SharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)

    fun getToken(key: String) : String?{
        return preferences.getString(key, "")
    }

    fun setToken(key: String, value : String){
        preferences.edit().putString(key, value).apply()
    }
}