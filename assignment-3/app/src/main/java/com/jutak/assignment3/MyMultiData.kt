package com.jutak.assignment3
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MyMultiData(val viewType: ViewType) {

    data class Voca(val owner: String, val name: String, val id: String)
    enum class ViewType { VOCABULARY, WORD, VOCABULARY_ADD, VOCABULARY_INFO }

    @JsonClass(generateAdapter = true)
    data class Vocabulary(
        @Json(name = "vocabulary_id") val id: Integer,
        @Json(name = "vocabulary_name") val name: String,
        @Json(name = "vocabulary_owner") val owner: String
    ) : MyMultiData(ViewType.VOCABULARY)

    @JsonClass(generateAdapter = true)
    data class Word(
        @Json(name = "word_spell") val spell: String,
        @Json(name = "word_meaning") val meaning: String,
        @Json(name = "word_synonym") val synonym: String?,
        @Json(name = "word_antonym") val antonym: String?,
        @Json(name = "word_sentence") val sentence: String?
    ) : MyMultiData(ViewType.WORD)

    @JsonClass(generateAdapter = true)
    data class VocabularyInfo(
        @Json(name = "info_id") val id: Integer,
        @Json(name = "info_name") val name: String,
        @Json(name = "info_owner") val owner: String,
        @Json(name = "info_word_list") val wordList: List<Word>
    ) : MyMultiData(ViewType.VOCABULARY_INFO)

    @JsonClass(generateAdapter = true)
    data class VocabularyAdd(
        @Json(name = "add_id") val id: Integer,
        @Json(name = "add_name") val name: String,
        @Json(name = "add_password") val password: String
    ) : MyMultiData(ViewType.VOCABULARY_ADD)
}

annotation class Json(val name: String)

annotation class JsonClass(val generateAdapter: Boolean)
