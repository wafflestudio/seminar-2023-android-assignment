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


    private val _apiKeyValid = MutableLiveData<Boolean>()
    val apiKeyValid: LiveData<Boolean>
        get() = _apiKeyValid

    fun saveApiKey(apiKey: String) {
        storage.setStoredTag("apiKey", apiKey)
    }

    suspend fun checkApiKey(apiKey: String): String { // 통신 관련 코드로 수정
        var apiReturn = "false"

        viewModelScope.launch {
            try {
                Log.d("LVM", "try success.")
                val loginResult = repository.authenticateApiKey("application/json", apiKey)
                apiReturn = loginResult.toString()
                Log.d("LVM", "$apiReturn")
                if (loginResult) {
                    saveApiKey(apiKey)
                }
                storage.setStoredBoolean("loginResult", loginResult)
            } catch (e: HttpException) {
                apiReturn = "HttpException"
            }
        }

        return apiReturn
    }
}