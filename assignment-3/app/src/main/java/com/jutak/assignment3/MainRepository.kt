package com.jutak.assignment3

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: MyRestAPI) {

    suspend fun getWordListInfo(): MutableList<MyData.WordListInfo> {
        return api.getWordListInfo().toMutableList()
    }

    suspend fun getWordList(id: Int): MyData.WordList {
        return api.getWordList(id)
    }

    suspend fun pushWordList(data: MyData.WordListPostInfo): List<MyData.WordListInfo> {
        return api.createWordList(data)
    }

}