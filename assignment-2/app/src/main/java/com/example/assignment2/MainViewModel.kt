package com.example.assignment2

import android.util.Log
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

    fun init(){
        _state.value = State.PLAYER1
        _board.value = MutableList(9){Mark.EMPTY}
    }

    private fun changeTurn(){
        when(_state.value!!){
            State.PLAYER1 -> _state.value = State.PLAYER2
            State.PLAYER2 -> _state.value = State.PLAYER1
            else -> Log.d("changeTurn", "error")
        }
    }




    fun boardItemClickEvent(position : Int){

        when(state.value){
            State.PLAYER1 -> _board.value!![position] = Mark.PLAYER1
            State.PLAYER2 -> _board.value!![position] = Mark.PLAYER2
            else -> return
        }
        if(isGameover()!=Mark.EMPTY){
            _state.value = State.GAMEOVER
        }
        else if(_board.value!!.all{it !=Mark.EMPTY}) _state.value = State.DRAW
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




}