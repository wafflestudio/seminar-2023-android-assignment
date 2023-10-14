package com.jutak.assignment3

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    public abstract suspend fun getVocaListSuspend(): List<MyMultiData.Voca>

    @POST("/myapp/v1/word_list")
    public abstract suspend fun addVoca(@Body data : MyMultiData.VocaAdd): Response<List<MyMultiData.Voca>>
}