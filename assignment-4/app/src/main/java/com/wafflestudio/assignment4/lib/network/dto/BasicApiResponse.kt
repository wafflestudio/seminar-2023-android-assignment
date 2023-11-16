package com.wafflestudio.assignment4.lib.network.dto

import com.squareup.moshi.Json

data class BasicApiResponse (
    @Json(name="success") val success: Boolean,
    @Json(name="status_code") val statusCode: Int,
    @Json(name="status_message") val statusMessage: String,
)