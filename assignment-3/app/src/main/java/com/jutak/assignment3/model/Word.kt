package com.jutak.assignment3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Word(
    @Json(name = "spell") val spell: String,
    @Json(name = "meaning") val meaning: String,
    @Json(name = "synonym") val synonym: String?,
    @Json(name = "antonym") val antonym: String?,
    @Json(name = "sentence") val sentence: String?,
)
