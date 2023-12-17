package com.wafflestudio.assignment4.utils

import android.content.Context

const val SHARED_PREFERENCE_NAME = "shared"

fun Context.readFromPreference(key: String): String? {
    return getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        ?.getString(key, null)
}

fun Context.writeToPreference(key: String, value: String) {
    with(getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit()) {
        putString(key, value)
        apply()
    }
}

fun Context.eraseFromPreference(key: String) {
    with(getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit()) {
        remove(key)
        apply()
    }
}