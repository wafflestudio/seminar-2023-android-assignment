package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Voca(
    @Json(name = "id") val id: Integer,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    //@Json(name = "word_list") val word_list: List<Word>,
)