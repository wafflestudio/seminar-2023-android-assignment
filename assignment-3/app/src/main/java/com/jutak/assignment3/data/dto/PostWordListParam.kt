package com.jutak.assignment3.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostWordListParam(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String,
)
