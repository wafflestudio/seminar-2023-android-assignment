package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WordList(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
)

@JsonClass(generateAdapter = true)
data class CreateWordList(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "pass") val pass: String,
)