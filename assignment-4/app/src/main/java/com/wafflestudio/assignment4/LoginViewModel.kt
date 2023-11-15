package com.wafflestudio.assignment4

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: RestAPI
):ViewModel() {
    suspend fun login(key:String?):Boolean{
        if(key==null) return false
        val answer=api.authentication("Bearer "+key)
        return answer.success
    }

    suspend fun getmovies():Movie{
        val answer=api.getmovies("Bearer "+MyApplication.prefs.getString("token",""))
        return answer
    }
}
