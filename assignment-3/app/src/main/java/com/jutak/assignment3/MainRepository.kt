package com.jutak.assignment3

import android.util.Log
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: MyRestAPI) {

    enum class Signs {
        SUCCESS,
        FAILURE,
        EXCEPTION
    }

    suspend fun getWordListInfo(): Pair<Signs, Any?>  {
        return proceedException(api.getWordListInfo())
    }

    suspend fun getWordList(id: Int): Pair<Signs, Any?>  {
        return proceedException(api.getWordList(id))
    }

    suspend fun pushWordList(data: MyData.WordListPostInfo): Pair<Signs, Any?>  {
        return proceedException(api.createWordList(data))
    }

    suspend fun verifyPassword(id: Int, password: String): Pair<Signs, Any?>  {
        return proceedException(api.verifyPermission(id, MyData.PasswordJSON(password)))
        // 왜 잘못 입력했는데 200이 뜨지...
    }

    suspend fun deleteWordList(id: Int, password: String): Pair<Signs, Any?>  {
        val response = api.deleteWordList(id, MyData.PasswordJSON(password))
        Log.d("Model", "${response.code()}")
        return proceedException(response)

    }

    suspend fun putWord(id: Int, word: MyData.WordPutInfo): Pair<Signs, Any?>  {
        return proceedException(api.putWord(id, word))
    }

    fun <T> proceedException(response: Response<T>): Pair<Signs, Any?> {
        try {
            if (response.isSuccessful) {
                Log.d("Model", "${response.body()}")
                return Pair(Signs.SUCCESS, response.body())
            } else {
                return Pair(Signs.FAILURE, response.errorBody()?.string() ?: response.code().toString())
            }
        } catch (e: IOException){
            return Pair(Signs.EXCEPTION, "Network Error")
        }
    }

}