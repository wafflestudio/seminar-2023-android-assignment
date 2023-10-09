package com.jutak.assignment3.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * 간단한 단어장 데이터 클래스.
 */
@JsonClass(generateAdapter = true)
data class WordListBrief(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
)
