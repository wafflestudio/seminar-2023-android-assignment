package com.jutak.assignment3.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SimpleWordList(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
)
