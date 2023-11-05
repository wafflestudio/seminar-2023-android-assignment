package com.jutak.assignment3.data.dto

import com.jutak.assignment3.data.model.Word
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostWordToWordListParam(
    @Json(name = "password") val password: String,
    @Json(name = "word") val word: Word,
)
