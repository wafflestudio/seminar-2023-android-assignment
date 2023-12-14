package com.example.myapplication

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getVocaLists(): Int

    @POST("/myapp/v1/word_list")
    suspend fun sendNewVocaList(): Int

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getVocaSpecificInfo(): Int


}