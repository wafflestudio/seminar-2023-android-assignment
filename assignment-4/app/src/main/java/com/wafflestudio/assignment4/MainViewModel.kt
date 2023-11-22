package com.wafflestudio.assignment4

import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment4.data.AuthRepository
import com.wafflestudio.assignment4.data.PrefStorage
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val storage: PrefStorage
) : ViewModel() {

    fun getLoginPref(): Boolean {
        val loginPref = storage.getStoredTag("loginPref")
        if (loginPref == "true") {
            return true
        }
        return false
    }
}