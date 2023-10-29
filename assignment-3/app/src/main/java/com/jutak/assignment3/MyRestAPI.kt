package com.jutak.assignment3
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

//JSON으로 소통하기에 POST 혹은 GET으로 들어오는 DATA는 Json 객체여야 함!
interface MyRestAPI {
    @GET("/myapp/v1/word_lists")
    suspend fun getWordListInfo(): Response<List<MyData.WordListInfo>>

    @GET("/myapp/v1/word_list/{id}")
    suspend fun getWordList(@Path("id") id: Int): Response<MyData.WordList>

    @POST("/myapp/v1/word_list")
    suspend fun createWordList(@Body data: MyData.WordListPostInfo): Response<List<MyData.WordListInfo>>

    @POST("/myapp/v1/word_list/{id}/permission")
    suspend fun verifyPermission(@Path("id") id: Int, @Body password: MyData.PasswordJSON): Response<MyData.Validation>

    // DELETE 메소드에서 Body를 사용하기 위한 방법
    @HTTP(method="DELETE", path="/myapp/v1/word_list/{id}", hasBody=true)
    suspend fun deleteWordList(@Path("id") id: Int, @Body data: MyData.PasswordJSON): Response<Unit>

    @PUT("/myapp/v1/word_list/{id}")
    suspend fun putWord(@Path("id") id: Int, @Body word: MyData.WordPutInfo): Response<MyData.WordList>

}