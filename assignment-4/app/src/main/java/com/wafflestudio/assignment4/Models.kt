package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Authentication(
    @Json(name="success") val success:Boolean,
    @Json(name="status_code") val code:Int,
    @Json(name="status_messsage") val message:String
)