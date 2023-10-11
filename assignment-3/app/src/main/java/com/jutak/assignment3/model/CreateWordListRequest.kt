package com.jutak.assignment3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateWordListRequest(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String,
)
