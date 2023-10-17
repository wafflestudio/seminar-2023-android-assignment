package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path


interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordLists(): List<WordListRead>

    @POST("/myapp/v1/word_list")
    suspend fun createWordList(@Body data : WordListWrite): Response<List<WordListRead>>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWords(@Path("id") id : String?): Response<WordListDetail>

    @POST("/myapp/v1/word_list/{id}/permission")
    suspend fun confirmPassword(@Path("id") id : String, @Body data : Password) : Response<Valid>

    @HTTP(method = "DELETE", path = "/myapp/v1/word_list/{id}", hasBody = true )
    suspend fun deleteList(@Path("id") id : String, @Body data : Password) : Response<Unit>

}