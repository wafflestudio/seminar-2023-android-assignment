package com.jutak.assignment3

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

sealed class MyMultiData(){
    @JsonClass(generateAdapter = true)
    data class WordListsInfo(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String,
        @Json(name = "owner")
        val owner: String,
    )

    @JsonClass(generateAdapter = true)
    data class WordListPost(
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
        @Json(name = "password") val password: String,
    )

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class WordInfo(
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
        @Json(name = "word_list") val word_list: List<Word>,
    ): Parcelable

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Word(
        @Json(name = "spell") val spell: String,
        @Json(name = "meaning") val meaning: String,
        @Json(name = "synonym") val synonym: String?,
        @Json(name = "antonym") val antonym: String?,
        @Json(name = "sentence") val sentence: String?,
    ):Parcelable
}
