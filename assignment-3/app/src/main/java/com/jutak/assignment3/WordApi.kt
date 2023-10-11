package com.jutak.assignment3

import com.jutak.assignment3.model.CreateWordListRequest
import com.jutak.assignment3.model.WordListBrief
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WordApi {

    @GET("/myapp/v1/word_lists")
    suspend fun getWordLists(): List<WordListBrief>

    @POST("/myapp/v1/word_list")
    suspend fun createWordList(@Body request: CreateWordListRequest): List<WordListBrief>

}