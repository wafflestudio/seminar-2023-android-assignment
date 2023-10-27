package com.example.voca

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getVocaLists(): List<MyDataTypes.VocaListInfo>

    @POST("/myapp/v1/word_list")
    suspend fun sendNewVocaList(@Body data : MyDataTypes.NewVocaList): Response<List<MyDataTypes.VocaListInfo>>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getVocaSpecificInfo(@Path("id") id : Int): MyDataTypes.VocaListSpecificInfo
}