package com.wafflestudio.assignment4

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

class AuthenticateResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "status_message") val statusMessage : String,
)
interface MyRestAPI {

    /*
    @GET("/myapp/v1/word_lists")
    suspend fun getWordLists(): List<WordListRead>

    @POST("/myapp/v1/word_list")
    suspend fun createWordList(@Body data: WordListWrite): Response<List<WordListRead>>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWords(@Path("id") id: String?): Response<WordListDetail>
    */

    @GET("/3/authentication")
    suspend fun authenticate(): Response<AuthenticateResponse>
}