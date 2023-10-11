package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordBookList(
    @Json(name = "id") val id : Int,
    @Json(name = "name") val name : String,
    @Json(name = "owner") val owner : String,
)