package com.wafflestudio.assignment4.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
)