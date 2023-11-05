package com.jutak.assignment3


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyRestAPI {
    @GET("word_lists")
    suspend fun getWordBookList(): List<WordBook>

    @POST("word_list")
    suspend fun wordBookCreate(@Body data: CreateWordBook): Response<List<WordBook>>

    @GET("wor_list/{id}")
    suspend fun getWordListById(@Path("id") id :Int,):WordList
}