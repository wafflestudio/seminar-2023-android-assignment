package com.jutak.assignment3.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class WordList(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String? = null,
    @Json(name = "word_list") val wordList: List<Word>,
) : Parcelable {
    companion object {
        val Default = WordList(0, "", "", null, emptyList())
    }
}
