package com.jutak.assignment3

import com.jutak.assignment3.model.WordListBrief
import retrofit2.http.GET

interface WordApi {

    @GET("/myapp/v1/word_lists")
    suspend fun getWordLists(): List<WordListBrief>

}