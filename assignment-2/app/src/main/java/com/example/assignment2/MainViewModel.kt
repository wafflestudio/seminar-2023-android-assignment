package com.example.assignment2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class State{
    GAMEOVER,
    PLAYER1,
    PLAYER2
}
enum class Mark{
    EMPTY,
    PLAYER1,
    PLAYER2
}
class MainViewModel: ViewModel() {
    private val _state= MutableLiveData<State>(State.PLAYER1)
    val state: LiveData<State> = _state

    private fun changeTurn(){
        when(_state.value!!){
            State.PLAYER1 -> _state.value = State.PLAYER2
            State.PLAYER2 -> _state.value = State.PLAYER1
            else -> Log.d("changeTurn", "error")
        }
    }


    private val _board = MutableLiveData<MutableList<Mark>>(MutableList(9){Mark.EMPTY})
    val board : LiveData<MutableList<Mark>> = _board


    fun boardItemClickEvent(position : Int){

        when(state.value){
            State.PLAYER1 -> _board.value!![position] = Mark.PLAYER1
            State.PLAYER2 -> _board.value!![position] = Mark.PLAYER2
            else -> return
        }
        changeTurn()
    }


//    fun clickBoard(place : Int){
//        if(_state.value == State.GAMEOVER) return
//        when(_board.value?.get(place)){
//            0 -> _board.value!![place] = _state.value?.ordinal!!
//            1,2 -> return
//        }
//    }




}