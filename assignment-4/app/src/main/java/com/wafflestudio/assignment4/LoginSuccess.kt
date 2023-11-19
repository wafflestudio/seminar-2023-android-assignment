package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class LoginSuccess(@Json(name = "success") val success: Boolean)