package com.wafflestudio.assignment4


import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val api : MyRestAPI,
) : ViewModel() {
    private val _succeed = MutableLiveData<Boolean>(false)
    val succeed:LiveData<Boolean> = _succeed

    suspend fun login(token:String){
        val loginSucceed = api.giveToken("Bearer $token").success
        withContext(Dispatchers.Main){
            if(loginSucceed){
                MyApplication.preference.saveToken(token)
                _succeed.value = true
            }
        }
    }
}