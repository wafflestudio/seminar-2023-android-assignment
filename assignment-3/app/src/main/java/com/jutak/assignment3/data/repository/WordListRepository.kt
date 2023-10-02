package com.jutak.assignment3.data.repository

import com.jutak.assignment3.data.model.SimpleWordList
import com.jutak.assignment3.data.model.Word
import com.jutak.assignment3.data.model.WordList

interface WordListRepository {
    suspend fun getRemoteWordLists(): List<SimpleWordList>
    suspend fun getRemoteWordListById(id: Int): WordList
    suspend fun postNewWordList(name: String, owner: String, password: String): List<SimpleWordList>
    suspend fun addWordToWordList(listId: Int, password: String, word: Word): WordList
    suspend fun deleteRemoteWordList(id: Int, password: String)
    suspend fun checkWordListEditPermission(id: Int, password: String): Boolean
}