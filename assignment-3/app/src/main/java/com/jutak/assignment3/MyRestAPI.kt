package com.jutak.assignment3

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class PostResult2(
    val id:Int,
    val name:String,
    val owner:String
)
interface MyRestAPI {
    @POST("word_list")
    suspend fun word_list_create(@Body data: MyModels.Data_newlist): List<MyModels.Wordlists>

    @GET("word_list/{id}")
    suspend fun word_list_read(@Path("id") id:Int): MyModels.Awordlist

    @PUT("word_list/{id}")
    fun word_list_update(@Body data: MyModels.Data_putword, @Path("id") id:Int):Call<MyModels.Awordlist>

    @HTTP(method = "DELETE", path = "word_list/{id}", hasBody = true)
    fun word_list_delete(@Body data: MyModels.Datapw, @Path("id") id:Int):Call<MyModels.DeleteResult>

    @POST("word_list/{id}/permission")
    suspend fun word_list_permission(@Body data: MyModels.Datapw, @Path("id") id:Int):MyModels.PerResult

    @GET("word_lists")
    suspend fun word_lists_list(): List<MyModels.Wordlists>
}
