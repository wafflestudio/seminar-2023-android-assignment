package com.example.voca

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyDataTypes(val viewType: ViewType){
    @JsonClass(generateAdapter = true)
    data class VocaListInfo(
        @Json(name = "id") val id: Integer,
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
    ): MyDataTypes(ViewType.A)

    @JsonClass(generateAdapter = true)
    data class NewVocaList(
        @Json(name = "owner") val owner: String,
        @Json(name = "name") val name: String,
        @Json(name = "password") val password: String,
    )
    @JsonClass(generateAdapter = true)
    data class VocaListSpecificInfo(
        @Json(name = "id") val id: Integer,
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
        @Json(name = "word_list") val word_list: List<Voca>,
    )

    @JsonClass(generateAdapter = true)
    data class Voca (
        @Json(name = "spell") val spell: String,
        @Json(name = "meaning") val meaning: String,
        @Json(name = "synonym") val synonym: String?,
        @Json(name = "antonym") val antonym: String?,
        @Json(name = "sentence") val sentence: String?,
    ) : MyDataTypes(ViewType.B)
    enum class ViewType { A,B}
}
