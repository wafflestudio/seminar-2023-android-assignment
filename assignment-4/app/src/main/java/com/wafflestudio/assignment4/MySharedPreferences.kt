package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences
import android.view.Display.Mode

class MySharedPreferences(context: Context) {
    private val preferences : SharedPreferences = context.getSharedPreferences("init_pref",Context.MODE_PRIVATE)

    fun putToken(key: String, value: String){
        preferences.edit().putString(key, value).apply()
    }
    fun getToken(key: String): String?{
        return preferences.getString(key,"")
    }
    fun removeToken(key:String){
        preferences.edit().remove(key).apply()
    }

}