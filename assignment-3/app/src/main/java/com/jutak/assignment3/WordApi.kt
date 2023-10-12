package com.jutak.assignment3

import com.jutak.assignment3.model.CreateWordListRequest
import com.jutak.assignment3.model.Word
import com.jutak.assignment3.model.WordListBrief
import com.jutak.assignment3.model.WordResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WordApi {

    @GET("/myapp/v1/word_lists")
    suspend fun getWordLists(): List<WordListBrief>

    @POST("/myapp/v1/word_list")
    suspend fun createWordList(@Body request: CreateWordListRequest): List<WordListBrief>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWords(@Path("id") id: Int): WordResponse

}