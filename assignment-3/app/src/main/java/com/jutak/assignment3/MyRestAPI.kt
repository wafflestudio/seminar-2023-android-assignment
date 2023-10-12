package com.jutak.assignment3

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordListsInfoSuspend(): List<WordListsInfo>
}