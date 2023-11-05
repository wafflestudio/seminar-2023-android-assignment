package com.jutak.assignment3.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Word(
    @Json(name = "spell") val spell: String,
    @Json(name = "meaning") val meaning: String,
    @Json(name = "synonym") val synonym: String? = null,
    @Json(name = "antonym") val antonym: String? = null,
    @Json(name = "sentence") val sentence: String? = null,
) : Parcelable
