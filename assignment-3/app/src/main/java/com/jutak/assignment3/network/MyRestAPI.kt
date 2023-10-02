package com.jutak.assignment3.network

import com.jutak.assignment3.data.dto.CheckWordListEditPermissionParam
import com.jutak.assignment3.data.dto.CheckWordListEditPermissionResult
import com.jutak.assignment3.data.dto.DeleteWordListParam
import com.jutak.assignment3.data.dto.DeleteWordListResult
import com.jutak.assignment3.data.dto.GetWordListByIdResult
import com.jutak.assignment3.data.dto.GetWordListsResult
import com.jutak.assignment3.data.dto.PostWordListParam
import com.jutak.assignment3.data.dto.PostWordListResult
import com.jutak.assignment3.data.dto.PostWordToWordListParam
import com.jutak.assignment3.data.dto.PutWordToWordListResult
import com.jutak.assignment3.data.model.SimpleWordList
import com.jutak.assignment3.data.model.WordList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordLists(): GetWordListsResult

    @POST("/myapp/v1/word_list")
    suspend fun postWordList(
        @Body param: PostWordListParam
    ): PostWordListResult

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWordListById(
        @Path("id") id: Int,
    ): GetWordListByIdResult

    @PUT("/myapp/v1/word_list/{id}")
    suspend fun putWordToWordList(
        @Path("id") id: Int,
        @Body param: PostWordToWordListParam
    ): PutWordToWordListResult

    @HTTP(method = "DELETE", path = "/myapp/v1/word_list/{id}", hasBody = true)
    suspend fun deleteWordList(
        @Body param: DeleteWordListParam,
        @Path("id") id: Int,
    )

    @POST("/myapp/v1/word_list/{id}/permission")
    suspend fun checkWordListEditPermission(
        @Body param: CheckWordListEditPermissionParam,
        @Path("id") id: Int,
    ): CheckWordListEditPermissionResult
}