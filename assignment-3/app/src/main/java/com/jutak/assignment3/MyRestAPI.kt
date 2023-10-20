package com.jutak.assignment3

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordListsInfoSuspend(): MutableList<MyMultiData.WordListsInfo>
    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWordsSuspend(@Path("id") id: Int): MyMultiData.WordInfo
    @POST("/myapp/v1/word_list")
    suspend fun postWordListSuspend(@Body data: MyMultiData.WordListPost): List<MyMultiData.WordListsInfo>
}