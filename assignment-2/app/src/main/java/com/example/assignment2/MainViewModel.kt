package com.example.assignment2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.widget.GridLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs


class MainViewModel(): ViewModel() {
    private var prevHistory = mutableListOf<Int>()
    private lateinit var bitmap: Bitmap
    private var num = 0
    private var _turn = 1
    private var turn = 1
    private var flag = 0
    private var _infoText = MutableLiveData<String>("")
    var infoText : LiveData<String> = _infoText
    private var _resetText = MutableLiveData<String>(" 초기화 ")
    var resetText: LiveData<String> = _resetText
    private var _resetColor = MutableLiveData<String>("#808080")
    var resetColor: LiveData<String> = _resetColor
    private val board = Array(3){ Array(3) {0} }
    private val _boardLiveData = MutableLiveData<Array<Array<Int>>>(board)
    val boardLiveData : LiveData<Array<Array<Int>>> = _boardLiveData
    var historyData: MutableList<History> = mutableListOf()
    private fun oWins(){
        flag = 1
        _infoText.value = "O 승리"
        _resetText.value = " 한판 더"
        _resetColor.value = "#0000FF"
        return
    }
    private fun xWins(){
        flag = -1
        _infoText.value = "X 승리"
        _resetText.value = " 한판 더"
        _resetColor.value = "#0000FF"
        return
    }
    fun clickWhoWins(next: Int): Boolean{
        val index = next - 1
        if(flag != 0) return false
        if(board[index / 3][index % 3] != 0) return false
        prevHistory.add(next)
        board[index / 3][index % 3] = turn
        _boardLiveData.value = board
        for (row in 0..2){
            when(board[row].sum()){
                3 -> {
                    oWins()
                    return true
                }
                -3 -> {
                    xWins()
                    return true
                }
                else -> {}
            }
        }
        for (col in 0..2){
            var colSum = 0
            for(row in 0..2){
                colSum += board[row][col]
            }
            when(colSum){
                3 -> {
                    oWins()
                    return true
            }
                -3 -> {
                    xWins()
                    return true
                }
                else-> {}
            }
        }

        for(step in 0..2 step 2){
            var diagonalSum = 0
            for(i in 0..2){
                diagonalSum+= board[abs(step-i)][i]
            }
            when(diagonalSum){
                3 -> {
                    oWins()
                    return true
                }
                -3 -> {
                    xWins()
                    return true
                }
                else-> {}
            }
        }
        turn *= -1
        when(turn){
            -1 ->{
                _infoText.value = "X 차례입니다"
                return true
            }
            1 -> {
                _infoText.value = "O 차례입니다"
                return true
            }
        }
        return false
    }
    fun clickAddData(source: GridLayout){
        bitmap = Bitmap.createBitmap(550, 550, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        source.draw(canvas)

        num ++
        historyData.add(History.TypeA(num))
        historyData.add(History.TypeB(bitmap))
        historyData.add(History.TypeC(num))
    }

    fun resetData(){
        num = 0
        flag = 0
        turn = 1
        _infoText.value = ""
        _resetText.value = " 초기화 "
        _resetColor.value = "#808080"
        for(row in 0..2){
            for(col in 0..2){
                board[row][col] = 0
            }
        }
        _boardLiveData.value = board
        historyData.clear()
    }
    fun rewindData(prev: Int){

        num = prev
        when(prev%2){
            0 -> turn = 1
            1 -> turn = -1
        }
        flag = 0
        _infoText.value = "과거"
        _resetText.value = " 초기화 "
        _resetColor.value = "#808080"

        prevHistory = prevHistory.subList(0, prev)
        for(row in 0..2){
            for(col in 0..2){
                board[row][col] = 0
            }
        }
        for(index in 0..< prev){

            val cell = prevHistory[index] - 1
            board[cell/3][cell%3] = _turn
            _turn *= -1
            _boardLiveData.value = board
        }
        _turn = 1
        historyData = historyData.subList(0, 3 * prev)
        //Log.d(historyData[3*prev - 1].toString(), "ang")
    }

}