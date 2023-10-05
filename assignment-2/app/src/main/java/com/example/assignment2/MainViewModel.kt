package com.example.assignment2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment2.MainModel


class MainViewModel() : ViewModel() {
    private val dataModel: MainModel = MainModel()
    private val _data = MutableLiveData<List<String>>(mutableListOf(" ", " ", " ", " ", " ", " ", " ", " ", " "))
    val data: LiveData<List<String>> get() = _data
    private val _pastData = MutableLiveData<List<BoardData>>()
    val pastData: LiveData<List<BoardData>> get() = _pastData

    init{
        _data.value = mutableListOf(" ", " ", " ", " ", " ", " ", " ", " ", " ")
    }

    fun handleClickEvent(position: Int) {
        dataModel.updatedataList(position)
        _data.value = dataModel.getdataList()
        dataModel.savedataList()
        dataModel.changeTeam()
        dataModel.checkGame()
    }

    fun getData(position: Int): String {
        return _data.value?.get(position) ?: " "
    }

    fun getDataList(): List<String> {
        return dataModel.getdataList()
    }

    fun refreshBoard() {
        val board = dataModel.getdataList()
        _data.value = board
    }

    fun initBoard() {
        dataModel.initdataList()
        refreshBoard()
    }

    fun checkGameStatus(): String {
        return dataModel.checkGame()
    }

    fun saveBoard() {
        dataModel.savedataList()
    }

    fun getPastDataList(): List<BoardData> {
        return dataModel.fetchPastdataList()
    }





}