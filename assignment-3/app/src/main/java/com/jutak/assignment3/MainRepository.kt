package com.jutak.assignment3

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
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

    suspend fun verifyPassword(id: Int, password: String): Boolean {
        return api.verifyPermission(id, password)
    }

    suspend fun deleteWordList(id: Int, password: String): Response<String> {
        return api.deleteWordList(id, password)
    }

    suspend fun putWord(id: Int, word: MyData.WordInfo): MutableList<MyData.WordInfo> {
        return api.putWord(id, word).word_list.toMutableList()
    }

}