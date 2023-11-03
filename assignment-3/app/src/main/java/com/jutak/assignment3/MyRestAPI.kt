package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.*

data class WordSet(
    val spell: String,
    val meaning: String,
    val synonym: String = "",
    val antonym: String = "",
    val sentence: String = "",
)

@JsonClass(generateAdapter = true)
data class VocabularySet(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val setName: String,
    @Json(name = "owner") val ownerName: String,
)

@JsonClass(generateAdapter = true)
data class VocabularySetDetails(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val setName: String,
    @Json(name = "owner") val ownerName: String,
    @Json(name = "word_list") val vocabularyList: List<WordSet>,
)

@JsonClass(generateAdapter = true)
data class PostWordSetInput(
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "password") val password: String
)


interface MyRestAPI {


    @GET("/myapp/v1/word_list/{id}")
    suspend fun fetchAllVocabularySets(@Query("wordSetId") wordSetId: Int = -1): List<VocabularySet>

    @GET("/myapp/v1/word_list/{setId}")
    suspend fun fetchVocabularySetDetails(@Path("setId") setId: Int): VocabularySetDetails

    @POST("/myapp/v1/word_list")
    suspend fun postWordSet(@Body newWordSet: MainViewModel.PostWordSetInput): VocabularySet

    @GET("/myapp/v1/word_list")
    suspend fun fetchAllVocabularySets(): List<VocabularySet>



    @PUT("/myapp/v1/word_list/{setId}")
    suspend fun updatedVocabulary(
        @Body updatedVocabulary: Word,
        @Path("setId") setId: Int
    ): VocabularySetDetails



}
