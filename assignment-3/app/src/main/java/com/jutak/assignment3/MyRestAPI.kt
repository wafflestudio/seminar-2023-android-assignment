package com.jutak.assignment3

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    public abstract suspend fun getVocaListSuspend(): List<MyMultiData.Voca>

    //파라미터 추가, 단어 상세 뜻 dialog하면 끝!
    @GET("/myapp/v1/word_list/{id}")
    public abstract suspend fun getVocaInfoSuspend(@Path("id") id : Int): MyMultiData.VocaInfo

    @POST("/myapp/v1/word_list")
    public abstract suspend fun addVoca(@Body data : MyMultiData.VocaAdd): Response<List<MyMultiData.Voca>>
}