package com.jutak.assignment3.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteWordListParam(
    @Json(name = "password") val password: String,
)
