package com.wafflestudio.assignment4.network.dto.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StatusResponse(
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "status_message") val statusMessage: String,
    @Json(name = "success") val success: Boolean,
)
