package com.example.assignment2

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class State{
    GAMEOVER,
    PLAYER1,
    PLAYER2,
    DRAW
}
enum class Mark{
    EMPTY,
    PLAYER1,
    PLAYER2
}
class MainViewModel: ViewModel() {
    private val _state= MutableLiveData<State>(State.PLAYER1)
    val state: LiveData<State> = _state

    private val _board = MutableLiveData<MutableList<Mark>>(MutableList(9){Mark.EMPTY})
    val board : LiveData<MutableList<Mark>> = _board

    private val _history = MutableLiveData<MutableList<HistoryData>>(mutableListOf(HistoryData.Comment("게임 시작")))
    val history : LiveData<MutableList<HistoryData>> = _history

    private var turnNum = 0

    fun init(){
        _state.value = State.PLAYER1
        _board.value = MutableList(9){Mark.EMPTY}
        turnNum = 0
        _history.value!!.clear()
        _history.value!!.add(HistoryData.Comment("게임 시작"))
    }

    private fun changeTurn(){
        when(_state.value){
            State.PLAYER1 -> _state.value = State.PLAYER2
            State.PLAYER2 -> _state.value = State.PLAYER1
            else -> Log.d("changeTurn", "error")
        }
    }


    fun boardItemClickEvent(position : Int){
        turnNum++
        when(state.value){
            State.PLAYER1 -> _board.value = _board.value!!.toMutableList().apply {
                this[position] = Mark.PLAYER1
            }
            State.PLAYER2 -> _board.value = _board.value!!.toMutableList().apply {
                this[position] = Mark.PLAYER2
            }
            else -> return
        }
        addHistory()
        if(isGameover()!=Mark.EMPTY){
            val winnerMark = when(state.value){
                State.PLAYER1 -> "O"
                State.PLAYER2 -> "X"
                else -> " "
            }
            _state.value = State.GAMEOVER
            _history.value!!.add(HistoryData.Comment(winnerMark + "의 승리!"))
        }
        else if(_board.value!!.all{it !=Mark.EMPTY}) {
            _state.value = State.DRAW
            _history.value!!.add(HistoryData.Comment("무승부!"))
        }
        else changeTurn()

    }



    private fun isGameover() : Mark{
        val b : List<Mark> = _board.value!!
        for (i in 0 until 3){
            if(b[i*3]!=Mark.EMPTY && b[i*3]==b[i*3+1]&&b[i*3+1]==b[i*3+2]) return b[i*3]
            if(b[i]!=Mark.EMPTY && b[i]==b[i+3]&&b[i+3]==b[i+6]) return b[i]
        }
        if(b[0]!=Mark.EMPTY && b[0]==b[4]&&b[4]==b[8]) return b[0]
        if(b[2]!=Mark.EMPTY && b[2]==b[4]&&b[4]==b[6]) return b[2]
        return Mark.EMPTY
    }

    private fun addHistory(){
        _history.value!!.add(HistoryData.TurnNum(turnNum))
        _history.value!!.add(HistoryData.BoardData(board.value!!))
    }

    fun goBack(position : Int){
        if(2*position+2 == _history.value!!.size){
            return
        }
        // 피 땀 눈물이 담긴 깊은복사 얕은복사 passbyreference, passbyvalue 등등 써가면서 구현한 되돌아가기 기능 ㅜㅜ
        val newHistory : MutableList<HistoryData> = mutableListOf()
        for(i in 0 ..2*position){
            if(_history.value!![i] is HistoryData.BoardData){
                newHistory.add(HistoryData.BoardData((_history.value!![i] as HistoryData.BoardData).board.toMutableList()))
            }
            else newHistory.add(_history.value!![i])
        }
        _history.value!!.clear()
        for(item in newHistory){
            _history.value!!.add(item)
        }
        _board.value = (_history.value!!.last() as HistoryData.BoardData).board.toMutableList()
        turnNum = position
        _state.value = when(position%2){
            0 -> State.PLAYER1
            1 -> State.PLAYER2
            else -> throw error("state setting error")
        }
    }
}