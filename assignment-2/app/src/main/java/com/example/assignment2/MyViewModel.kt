package com.example.assignment2

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var board =mutableListOf(0,0,0,0,0,0,0,0,0)
    var liveboard :MutableLiveData<MutableList<Int>> =MutableLiveData()
    var history:MutableList<MutableList<Int>> = mutableListOf()
    var gamekey:Int=0
    var livegamekey:MutableLiveData<Int> = MutableLiveData()
    val checks=listOf(listOf(0,1,2),listOf(3,4,5),listOf(6,7,8),listOf(0,3,6),listOf(1,4,7)
        ,listOf(2,5,8),listOf(0,4,8),listOf(2,4,6))
    init{
        this.sync()
        history.add(mutableListOf())
    }

    fun game_finish_check(){
        this.checks.forEach{
            if(this.gamekey==0) {
                this.gamekey = when (this.board.slice(it)) {
                    listOf(-1, -1, -1) -> -1
                    listOf(1, 1, 1) -> 1
                    else -> 0
                }
            }
        }
    }

    fun turn(): Int {
        return (10-this.board.count{it==0})
    }

    fun process(num:Int){
        if(this.gamekey==0 && board[num-1]==0){
            this.board[num-1]=(this.turn()%2)*2-1
            this.history[0].add(num)
            this.game_finish_check()
            sync()
        }
    }

    fun reset(h: List<Int> =emptyList()){
        this.board=mutableListOf(0,0,0,0,0,0,0,0,0)
        this.history=listOf(h.toMutableList()).toMutableList()
        this.gamekey=0
        sync()
    }

    fun sync(){
        livegamekey.value=gamekey
        liveboard.value=board
    }
}