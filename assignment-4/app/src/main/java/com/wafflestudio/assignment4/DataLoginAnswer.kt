package com.wafflestudio.assignment4

import com.squareup.moshi.Json

class DataLoginAnswer (
    @Json(name = "success") val success: Boolean,
    /*@Json(name = "status_code") val status_code: Integer,
    @Json(name = "status_message") val status_message: String,*/
){
}