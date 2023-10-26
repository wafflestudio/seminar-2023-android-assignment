package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// 최스 스펙
@JsonClass(generateAdapter = true)
data class Word(
    @Json(name = "spell") val spell: String,
    @Json(name = "meaning") val meaning: String,
    @Json(name = "synonym") val synonym: String?,
    @Json(name = "antonym") val antonym: String?,
    @Json(name = "sentence") val sentence: String?
)

@JsonClass(generateAdapter = true)
data class ExList(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String
)

@JsonClass(generateAdapter = true)
data class NewList(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class ListDetail(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "word_list") val words: List<Word>
)

// 추가 스펙
@JsonClass(generateAdapter = true)
data class Password(
    @Json(name = "password") val password: String
)

@JsonClass(generateAdapter = true)
data class Permission(
    @Json(name = "valid") val valid: Boolean
)

@JsonClass(generateAdapter = true)
data class AddWord(
    @Json(name = "password") val password: String,
    @Json(name = "word") val word: Word,
)
