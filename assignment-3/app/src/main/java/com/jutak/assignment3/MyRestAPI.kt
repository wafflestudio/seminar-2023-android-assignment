package com.jutak.assignment3

import android.graphics.ColorSpace.Model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.internal.http.hasBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


@JsonClass(generateAdapter = true)
data class Word(
    @Json(name="spell") val spell:String,
    @Json(name="meaning") val meaning:String,
    @Json(name="synonym") val synonym:String?,
    @Json(name="antonym") val antonym:String?,
    @Json(name="sentence") val sentence:String?
)
@JsonClass(generateAdapter = true)
data class GetModel(
    @Json(name="id") val id: Int,
    @Json(name="name") val name: String,
    @Json(name="owner") val owner: String,
    @Json(name="word_list") val word_list: List<Word>
)
@JsonClass(generateAdapter = true)
data class Data1(
    @Json(name="name") val name:String,
    @Json(name="owner") val owner:String,
    @Json(name="password") val password:String
)

@JsonClass(generateAdapter = true)
data class Data2(
    @Json(name="password") val password: String,
    @Json(name="word") val word:Word
)
@JsonClass(generateAdapter = true)
data class pwData(
    @Json(name="password") val password: String
)

@JsonClass(generateAdapter = true)
data class DeleteResult(
    @Json() val result: String
)

@JsonClass(generateAdapter = true)
data class PerResult(
    @Json(name="valid") val valid: Boolean
)

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
/*
@JsonClass(generateAdapter = true)
data class PostResult(
    @Json() val result:List<PostModel>
)

 */

interface MyRestAPI {
    @POST("word_list")
    fun word_list_create(@Body data:Data1): Call<PostResult>

    @GET("word_list/{id}")
    fun word_list_read(@Path("id") id:Int): Call<GetModel>

    @PUT("word_list/{id}")
    fun word_list_update(@Body data:Data2, @Path("id") id:Int):Call<GetModel>

    @HTTP(method = "DELETE", path = "word_list/{id}", hasBody = true)
    fun word_list_delete(@Body data:pwData, @Path("id") id:Int):Call<DeleteResult>

    @POST("word_list/{id}/permission")
    fun word_list_permission_create(@Body data:pwData, @Path("id") id:Int):Call<PerResult>
}
