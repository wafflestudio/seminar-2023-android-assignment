package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class GameStatus{
    O_TURN, X_TURN, END, DRAW
}
class MainViewModel : ViewModel() {
    // n-th 턴 정보
    private val _turn = MutableLiveData<Int>(1)
    val turn: LiveData<Int> = _turn

    fun nextTurn() {
        _turn.value = _turn.value?.plus(1)
        if (_gameStatus.value == GameStatus.O_TURN){
            _gameStatus.value = GameStatus.X_TURN
        }
        else if(_gameStatus.value == GameStatus.X_TURN){
            _gameStatus.value = GameStatus.O_TURN
        }
    }

    // 게임 상태 정보
    private val _gameStatus = MutableLiveData<GameStatus>(GameStatus.O_TURN)
    val gameStatus: LiveData<GameStatus> = _gameStatus

    fun endGame() {
        _gameStatus.value = GameStatus.END
        array1 = BooleanArray(9) { true }
        array2 = BooleanArray(9) { true }
    }
    fun drawGame() {
        _gameStatus.value = GameStatus.DRAW
    }

    // 보드 정보
    var array1: BooleanArray = BooleanArray(9) {false}
    var array2: BooleanArray = BooleanArray(9) {false}

    // 승리 조건
    private val condition1 = intArrayOf(0, 1, 2)
    private val condition2 = intArrayOf(3, 4, 5)
    private val condition3 = intArrayOf(6, 7, 8)
    private val condition4 = intArrayOf(0, 3, 6)
    private val condition5 = intArrayOf(1, 4, 7)
    private val condition6 = intArrayOf(2, 5, 8)
    private val condition7 = intArrayOf(0, 4, 8)
    private val condition8 = intArrayOf(2, 4, 6)

    fun checkCondition(array: BooleanArray): Boolean {
        val con1 = condition1.all {array[it]}
        val con2 = condition2.all {array[it]}
        val con3 = condition3.all {array[it]}
        val con4 = condition4.all {array[it]}
        val con5 = condition5.all {array[it]}
        val con6 = condition6.all {array[it]}
        val con7 = condition7.all {array[it]}
        val con8 = condition8.all {array[it]}

        return con1 || con2 || con3 || con4 || con5 || con6 || con7 || con8
    }

    // 초기화
    fun initialization() {
        _turn.value = 1
        _gameStatus.value = GameStatus.O_TURN
        array1 = BooleanArray(9) {false}
        array2 = BooleanArray(9) {false}
        data.clear()
        data.add(MyMultiData.TypeA(0))
    }

    // 기록
    val data = mutableListOf<MyMultiData>(MyMultiData.TypeA(0))
}