package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


data class Word(
    val spell: String,
    val meaning: String,
    val synonym: String = "",
    val antonym: String = "",
    val sentence: String = "",
)

@JsonClass(generateAdapter = true)
data class WordSet(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
)

@JsonClass(generateAdapter = true)
data class WordSetDetail(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "word_list") val wordList: List<Word>,
)

@JsonClass(generateAdapter = true)
data class PermissionReturn(
    @Json(name = "valid") val valid: Boolean,
)

data class PostWordSetInput(val name: String, val owner: String, val password: String, )
data class PutWordInput(val password: String, val word: Word, )
data class PasswordInput(val password: String, )

interface MyRestAPI {

    @POST("/myapp/v1/word_list")
    suspend fun postWordSet(
        @Body wordSet: PostWordSetInput
    ): List<WordSet>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWordSet(
        @Path("id") id: Int
    ): WordSetDetail

    @PUT("/myapp/v1/word_list/{id}")
    suspend fun putWord(
        @Body input: PutWordInput,
        @Path("id") id: Int
    ): WordSetDetail

    @HTTP(method = "DELETE", path = "/myapp/v1/word_list/{id}", hasBody = true)
    //@DELETE("/myapp/v1/word_list/{id}")
    suspend fun deleteWordSet(
        @Body password: PasswordInput,
        @Path("id") id: Int
    )

    @POST("/myapp/v1/word_list/{id}/permission")
    suspend fun hasPermission(
        @Body password: PasswordInput,
        @Path("id") id: Int
    ): PermissionReturn

    @GET("/myapp/v1/word_lists")
    suspend fun getWordSetList(): List<WordSet>

}