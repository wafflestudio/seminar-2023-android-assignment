package com.jutak.assignment3

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    public abstract suspend fun getVocaListSuspend(): List<MyMultiData.Voca>

    @GET("/myapp/v1/word_list/{id}")
    public abstract suspend fun getWordListSuspend(@Path("id") id : String?): MyMultiData.VocaInfo

    @POST("/myapp/v1/word_list")
    public abstract suspend fun addVoca(@Body data : MyMultiData.VocaAdd): Response<List<MyMultiData.Voca>>
}