package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.GET





interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getVocaBookListSuspend(): List<MultiData.WordListRead>

}