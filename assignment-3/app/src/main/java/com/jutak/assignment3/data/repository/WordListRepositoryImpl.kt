package com.jutak.assignment3.data.repository

import com.jutak.assignment3.data.dto.CheckWordListEditPermissionParam
import com.jutak.assignment3.data.dto.CheckWordListEditPermissionResult
import com.jutak.assignment3.data.dto.DeleteWordListParam
import com.jutak.assignment3.data.dto.GetWordListByIdResult
import com.jutak.assignment3.data.dto.PostWordListParam
import com.jutak.assignment3.data.dto.PostWordToWordListParam
import com.jutak.assignment3.data.model.SimpleWordList
import com.jutak.assignment3.data.model.Word
import com.jutak.assignment3.data.model.WordList
import com.jutak.assignment3.network.MyRestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class WordListRepositoryImpl @Inject constructor(
    private val api: MyRestAPI,
) : WordListRepository {
    override suspend fun getRemoteWordLists(): List<SimpleWordList> {
        return api.getWordLists()
    }

    override suspend fun getRemoteWordListById(id: Int): WordList {
        val result = api.getWordListById(id)
        return WordList(result.id, result.name, result.owner, null, result.wordList)
    }

    override suspend fun postNewWordList(name: String, owner: String, password: String): List<SimpleWordList> {
        return api.postWordList(PostWordListParam(name, owner, password))
    }

    override suspend fun addWordToWordList(listId: Int, password: String, word: Word): WordList {
        val result = api.putWordToWordList(listId, PostWordToWordListParam(password, word))
        return WordList(result.id, result.name, result.owner,null, result.wordList)
    }

    override suspend fun deleteRemoteWordList(id: Int, password: String) {
        api.deleteWordList(DeleteWordListParam(password), id)
    }

    override suspend fun checkWordListEditPermission(id: Int, password: String): Boolean {
        return api.checkWordListEditPermission(CheckWordListEditPermissionParam(password), id)
            .valid
    }
}