package com.wafflestudio.assignment4

import com.squareup.moshi.Json

class LoginData (
    @Json(name = "success") val login_success: Boolean
){
}