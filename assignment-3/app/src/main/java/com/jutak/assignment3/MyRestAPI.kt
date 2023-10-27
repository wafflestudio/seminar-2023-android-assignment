package com.jutak.assignment3


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyRestAPI {
    @GET("word_lists")
    suspend fun getWordList(): List<WordList>

    @POST("word_lists")
    suspend fun wordListCreate(@Body data: CreateWordList): Response<List<WordList>>
}