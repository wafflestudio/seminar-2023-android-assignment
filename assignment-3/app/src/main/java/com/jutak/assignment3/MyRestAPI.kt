package com.jutak.assignment3

import retrofit2.Call
import retrofit2.http.GET

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    fun getVoca(): Call<Voca>

    @GET("/myapp/v1/word_lists")
    public abstract suspend fun getVocaListSuspend(): List<Voca>
}