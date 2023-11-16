package com.wafflestudio.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: MyRestAPI
): ViewModel() {
    private val _loginToken: MutableLiveData<String?> = MutableLiveData()
    val loginToken: MutableLiveData<String?> = _loginToken
/*
    init {
        initializeToken()
    }



    private fun initializeToken() {
        _loginToken.value = MyApplication.preferences.GetToken("token", "")
    }

 */

    suspend fun performLogin(authToken: String?): Boolean {
        if (authToken.isNullOrEmpty()) return false
        val loginResponse = loginService.authenticateUser("Bearer"+ authToken)
        if (loginResponse.login_success) {
            updatePreferences(authToken)
            _loginToken.postValue(authToken)
        }
        return loginResponse.login_success
    }

    private fun updatePreferences(authToken: String) {
        MyApplication.preferences.SetToken("token", authToken)
        MyApplication.preferences.SetToken("success", "true")
    }
}
