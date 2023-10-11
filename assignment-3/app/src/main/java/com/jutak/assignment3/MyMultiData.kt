package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyMultiData(){
    @JsonClass(generateAdapter = true)
    data class Voca(
        @Json(name = "id") val id: Integer,
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
    )

    @JsonClass(generateAdapter = true)
    data class VocaAdd(
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
        @Json(name = "password") val password: String,
    )

    @JsonClass(generateAdapter = true)
    data class Word (
        @Json(name = "spell") val spell: String,
        @Json(name = "meaning") val meaning: String,
        @Json(name = "synonym") val synonym: String?,
        @Json(name = "antonym") val antonym: String?,
        @Json(name = "sentence") val sentence: String?,
    )
}