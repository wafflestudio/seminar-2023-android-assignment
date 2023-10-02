package com.jutak.assignment3.data.dto

import com.jutak.assignment3.data.model.Word
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetWordListByIdResult(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "word_list") val wordList: List<Word>,
)