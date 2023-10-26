package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
sealed class MyModels {
    @JsonClass(generateAdapter = true)
    data class Word(
        @Json(name="spell") val spell:String,
        @Json(name="meaning") val meaning:String,
        @Json(name="synonym") val synonym:String?,
        @Json(name="antonym") val antonym:String?,
        @Json(name="sentence") val sentence:String?
    )

    @JsonClass(generateAdapter = true)
    data class Awordlist(
        @Json(name="id") val id: Int,
        @Json(name="name") val name: String,
        @Json(name="owner") val owner: String,
        @Json(name="word_list") val word_list: List<Word>
    )

    @JsonClass(generateAdapter = true)
    data class Wordlists(
        @Json(name="id") val id: Int,
        @Json(name="name") val name: String,
        @Json(name="owner") val owner: String,
    )

    @JsonClass(generateAdapter = true)
    data class Data_newlist(
        @Json(name="name") val name:String,
        @Json(name="owner") val owner:String,
        @Json(name="password") val password:String
    )

    @JsonClass(generateAdapter = true)
    data class Data_putword(
        @Json(name="password") val password: String,
        @Json(name="word") val word: Word
    )

    @JsonClass(generateAdapter = true)
    data class Datapw(
        @Json(name="password") val password: String
    )

    @JsonClass(generateAdapter = true)
    data class PerResult(
        @Json(name="valid") val valid: Boolean
    )
}