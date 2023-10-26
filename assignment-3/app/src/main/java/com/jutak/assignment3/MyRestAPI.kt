package com.jutak.assignment3

import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.converter.moshi.MoshiConverterFactory

interface MyRestAPI {
    @GET("vocabulary")
    suspend fun getVocaListSuspend(): List<MyMultiData.Voca>

    @GET("vocabulary/{vocabularyId}")
    suspend fun getWordListSuspend(@Path("vocabularyId") vocabularyId: String): MyMultiData.Word

    @GET("vocabulary/{vocabularyId}")
    suspend fun getVocaInfoSuspend(@Path("vocabularyId") vocabularyId: Int): MyMultiData.VocabularyInfo

    @POST("vocabulary")
    suspend fun addWordSet(@Body newWordSet: MyMultiData.Voca): MyMultiData.Voca
}

object ApiClient {
    private const val BASE_URL = "http://ec2-13-209-69-159.ap-northeast-2.compute.amazonaws.com:8000/myapp/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val vocabularyApiService: MyRestAPI = retrofit.create(MyRestAPI::class.java)
}
