package com.wafflestudio.assignment4


import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val api : MyRestAPI
) : ViewModel() {

    suspend fun login(token:String):Boolean{
        val loginSucceed = api.giveToken("Bearer $token").success
        if(loginSucceed){
            val sharedPRef = context.getSharedPreferences("myPref", Context.MODE_PRIVATE)
            sharedPRef.edit().putString("token",token).apply()
        }
        return loginSucceed
    }
}