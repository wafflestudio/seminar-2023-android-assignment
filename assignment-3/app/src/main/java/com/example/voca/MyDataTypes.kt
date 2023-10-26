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
        @Json(name = "name") val name: String,
        @Json(name = "owner") val owner: String,
        @Json(name = "password") val password: String,
    )
    enum class ViewType { A}
}
