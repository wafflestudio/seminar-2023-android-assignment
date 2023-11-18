package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences

class MySharedPreference(context: Context) {
    private  var prefs:SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)

    companion object{
        const val Token = "token"
    }

    fun saveToken(token:String){
        prefs.edit().putString(Token,token).apply()
    }

    fun getToken():String?{
        return prefs.getString(Token, null)
    }

    fun deleteToken(){
        prefs.edit().remove(Token).apply()
    }
}