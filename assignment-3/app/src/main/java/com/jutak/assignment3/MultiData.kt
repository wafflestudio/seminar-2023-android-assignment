package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WordListRead(
    @Json(name = "id") val id : Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
)
@JsonClass(generateAdapter = true)
data class WordListWrite(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String,
)
@JsonClass(generateAdapter = true)
data class WordListDetail(
    @Json(name = "id") val id : Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "word_list") val words : List<Word>
)
@JsonClass(generateAdapter = true)
data class Word(
    @Json(name = "spell") val spell: String,
    @Json(name = "meaning") val meaning: String,
    @Json(name = "synonym") val synonym: String?,
    @Json(name = "antonym") val antonym: String?,
    @Json(name = "sentence") val sentence: String?,
)
