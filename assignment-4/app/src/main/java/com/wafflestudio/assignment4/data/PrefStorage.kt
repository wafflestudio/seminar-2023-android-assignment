package com.wafflestudio.assignment4.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    fun getStoredTag(tag: String): String {
        return sharedPreferences.getString(tag, "")!!
    }
    fun setStoredTag(tag: String, query: String) {
        sharedPreferences.edit().putString(tag, query).apply()
    }

    fun setStoredBoolean(tag: String, bool: Boolean) {
        sharedPreferences.edit().putBoolean(tag, bool).apply()
    }

    fun deleteStoredTag(tag: String) {
        sharedPreferences.edit().remove(tag).apply()
    }

}