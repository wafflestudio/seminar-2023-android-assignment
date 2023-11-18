package com.example.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyDataTypes{
    @JsonClass(generateAdapter = true)
    data class APIResponse(
        @Json(name = "success") val success: Boolean,
        @Json(name = "status_code") val status_code: Int,
        @Json(name = "status_message") val status_message: String,
    )
}
