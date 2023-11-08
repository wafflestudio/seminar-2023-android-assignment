package com.wafflestudio.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var api : MyRestAPI
): ViewModel() {
    private val _token: MutableLiveData<String> = MutableLiveData<String>()
    val token : LiveData<String> = _token

    init {
        _token.postValue(MyApplication.preferences.getToken("token", ""))
    }

    suspend fun login(temp : String?) : Boolean {
        if(temp == null) return false
        var answer = api.getLogin("Bearer " + temp)
        if(answer.success) {
            MyApplication.preferences.setToken("token", temp)
            MyApplication.preferences.setToken("success", "true")
            _token.postValue(temp!!)
        }
        return answer.success
    }
}