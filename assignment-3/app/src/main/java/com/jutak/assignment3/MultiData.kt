package com.jutak.assignment3

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class WordBook(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
)

@JsonClass(generateAdapter = true)
data class CreateWordBook(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String,
)

@JsonClass(generateAdapter = true)
data class Word(
    @Json(name = "spell") val spell: String,
    @Json(name = "meaning") val meaning: String,
    @Json(name = "synonym") val synonym: String? = null,
    @Json(name = "antonym") val antonym: String? = null,
    @Json(name = "sentence") val sentence: String? = null,
)

@JsonClass(generateAdapter = true)
data class WordList(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "word_list") val wordList: List<Word>,
)