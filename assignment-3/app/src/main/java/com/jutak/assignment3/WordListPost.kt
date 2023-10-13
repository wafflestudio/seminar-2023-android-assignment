package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class WordListPost(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "pw") val pw: String,
)