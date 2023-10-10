package com.jutak.assignment3

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

@JsonClass(generateAdapter = true)
data class PostResult2(
    @Json(name="id") val id:Int,
    @Json(name="name") val name:String,
    @Json(name="owner") val owner:String
)

data class PostResult(
    val id:Int,
    val name:String,
    val owner:String
)
interface MyRestAPI {
    @POST("word_list")
    fun word_list_create(@Body data: MyModels.Data_newlist): Call<PostResult>

    @GET("word_list/{id}")
    fun word_list_read(@Path("id") id:Int): Call<MyModels.Awordlist>

    @PUT("word_list/{id}")
    fun word_list_update(@Body data: MyModels.Data_putword, @Path("id") id:Int):Call<MyModels.Awordlist>

    @HTTP(method = "DELETE", path = "word_list/{id}", hasBody = true)
    fun word_list_delete(@Body data: MyModels.Datapw, @Path("id") id:Int):Call<MyModels.DeleteResult>

    @POST("word_list/{id}/permission")
    fun word_list_permission_create(@Body data: MyModels.Datapw, @Path("id") id:Int):Call<MyModels.PerResult>

    @GET("word_lists")
    fun word_lists_list(): Call<List<MyModels.Wordlists>>
}
