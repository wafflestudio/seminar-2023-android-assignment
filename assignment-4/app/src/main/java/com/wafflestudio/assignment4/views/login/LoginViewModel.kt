package com.wafflestudio.assignment4.views.login

import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment4.network.ErrorParsedHttpException
import com.wafflestudio.assignment4.network.MyRestAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val myRestAPI: MyRestAPI,
) : ViewModel() {

    suspend fun authenticate(): Pair<Boolean, String> {
        val result = try {
            myRestAPI.authenticate()
        } catch (e: ErrorParsedHttpException) {
            e.errorDTO!!
        }
        return result.success to result.statusMessage
    }
}