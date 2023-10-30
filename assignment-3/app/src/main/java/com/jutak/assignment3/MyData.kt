package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
sealed class MyData(val viewType: ViewType) {

    // Parcelable 하게 만들어야 intent로 전달 가능...
    @JsonClass(generateAdapter = true)
    data class WordListInfo(
        @Json(name="id") val id: Int,
        @Json(name="name") val name: String,
        @Json(name="owner") val owner: String,
    ) : MyData(ViewType.WORD_LIST_INFO)

    @JsonClass(generateAdapter = true)
    data class WordList(
        @Json(name="id") val id: Int,
        @Json(name="name") val name: String,
        @Json(name="owner") val owner: String,
        @Json(name="word_list") val word_list: List<WordInfo>
    ) : MyData(ViewType.WORD_LIST)

    @JsonClass(generateAdapter = true)
    data class WordInfo(
        @Json(name="spell") val spell: String,
        @Json(name="meaning") val meaning: String,
        @Json(name="synonym") val synonym: String?,
        @Json(name="antonym") val antonym: String?,
        @Json(name="sentence") val sentence: String?
    ) : MyData(ViewType.WORD_INFO)

    @JsonClass(generateAdapter = true)
    data class WordListPostInfo(
        @Json(name="name") val name: String,
        @Json(name="owner") val owner: String,
        @Json(name="password") val word_list: String
    ) : MyData(ViewType.WORD_LIST_POST_INFO)

    @JsonClass(generateAdapter = true)
    data class PasswordJSON(
        @Json(name="password") val password: String
    )

    @JsonClass(generateAdapter = true)
    data class WordPutInfo(
        @Json(name="password") val password: String,
        @Json(name="word") val word: WordInfo
    )

    @JsonClass(generateAdapter = true)
    data class Validation(
        @Json(name="valid") val valid: Boolean
    )

    enum class ViewType {
        WORD_LIST_INFO,
        WORD_LIST,
        WORD_INFO,
        WORD_LIST_POST_INFO,
    }
}
