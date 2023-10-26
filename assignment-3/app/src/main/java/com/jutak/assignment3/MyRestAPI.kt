package com.jutak.assignment3

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.DELETE
import retrofit2.http.Path

interface MyRestAPI {
    // 최소 스펙
    @GET("/word_lists")
    suspend fun getWordLists(): List<ExList>

    @POST("/word_list")
    suspend fun postWordList(
        @Body newList : NewList
    ) : Response<List<ExList>>

    @GET("/word_list/{id}")
    suspend fun getWordList(
        @Path("id") id : Int
    ): ListDetail

    // 추가 스펙
    @POST("/word_list/{id}/permission")
    suspend fun checkPermission()

    @DELETE("/word_list/{id}")
    suspend fun deleteWordList()

    @PUT("/word_list/{id}")
    suspend fun putWord()
}