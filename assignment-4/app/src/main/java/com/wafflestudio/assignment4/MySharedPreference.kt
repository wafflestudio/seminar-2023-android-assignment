package com.wafflestudio.assignment4

import android.content.Context

class MySharedPreference(context: Context) {
    val sharedPRef = context.getSharedPreferences("what name",Context.MODE_PRIVATE)


}