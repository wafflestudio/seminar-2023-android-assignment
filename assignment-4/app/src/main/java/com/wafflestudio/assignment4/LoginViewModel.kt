package com.wafflestudio.assignment4

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.assignment4.data.AuthRepository
import com.wafflestudio.assignment4.data.MainRepository
import com.wafflestudio.assignment4.data.MainRepositoryImpl
import com.wafflestudio.assignment4.data.PrefStorage
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val api: MovieRestApi,
    private val repository: AuthRepository,
    private val storage: PrefStorage
) : ViewModel() {


    private val _apiKeyValid = MutableLiveData<String>()
    val apiKeyValid: LiveData<String>
        get() = _apiKeyValid

    fun saveApiKey(apiKey: String) {
        storage.setStoredTag("apiKey", apiKey)
        storage.setStoredTag("loginPref", "true")
    }

    suspend fun checkApiKey(apiKey: String) { // 통신 관련 코드로 수정

        viewModelScope.launch {
            try {
                 Log.d("LVM", "try success.")
                val loginResult = repository.authenticateApiKey("application/json", apiKey)
                _apiKeyValid.value = loginResult.toString()
                Log.d("LVM", "apiReturn: ${_apiKeyValid.value}")
                if (loginResult) {
                    saveApiKey(apiKey)
                }
            } catch (e: HttpException) {
                _apiKeyValid.value = "HttpException"
            }
        }
    }
}