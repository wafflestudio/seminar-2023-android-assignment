package com.jutak.assignment3

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordListsInfoSuspend(): MutableList<WordListsInfo>
    @POST("/myapp/v1/word_list")
    suspend fun postWordListSuspend(@Body data: WordListPost): MutableList<WordListsInfo>
}