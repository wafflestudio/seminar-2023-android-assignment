package com.wafflestudio.assignment4

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment4.data.MainRepository
import com.wafflestudio.assignment4.data.MainRepositoryImpl
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: MovieRestApi,
    private val repository: MainRepository
) : ViewModel() {


    private val _apiKeyValid = MutableLiveData<Boolean>()
    val apiKeyValid: LiveData<Boolean>
        get() = _apiKeyValid

    fun saveApiKey(apiKey: String, sharedPref: SharedPreferences) {
        sharedPref.edit().putString("apiKey", apiKey).apply()
        _apiKeyValid.value = true // API 키가 저장되었음을 표시
    }

    fun checkApiKey(savedApiKey: String?): Boolean { // 통신 관련 코드로 수정
        return apiKey == savedApiKey
    }
}