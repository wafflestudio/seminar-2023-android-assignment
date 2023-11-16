package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences
class SharedPreference (context: Context){
    private val preferences: SharedPreferences=
        context.getSharedPreferences("preferences name",Context.MODE_PRIVATE)

    fun SetToken(key: String, defValue: String){
        preferences.edit().putString(key, defValue).apply()
    }

    fun GetToken(key: String, defValue: String):String{
        return preferences.getString(key, defValue).toString()
    }

    fun DeleteToken(key: String){
        preferences.edit().remove(key).apply()
    }
}