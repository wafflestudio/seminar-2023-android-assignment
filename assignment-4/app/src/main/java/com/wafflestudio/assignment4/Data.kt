package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class Data {

    @JsonClass(generateAdapter = true)
    data class loginResult(
        @Json(name = "success") val success: Boolean
    )
}