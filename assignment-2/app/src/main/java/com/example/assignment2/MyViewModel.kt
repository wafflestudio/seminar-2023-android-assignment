package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    // Initialize game state with LiveData
    private val _currentGameState = MutableLiveData(GameState(0, Progress.OPLAYING, Array(3) {Array(3) {Progress.NULL}}))
    val currentGameState: LiveData<GameState> = _currentGameState
    val history: ArrayList<GameState> = arrayListOf(_currentGameState.value!!)

    // GameState를 바꾸는 함수
    fun changeGameState(row: Int, col: Int) {
        // 클릭된 위치가 비어있는지 확인
        if (_currentGameState.value!!.board[row][col] != Progress.NULL) return

        // 게임이 끝났는지 확인
        if (_currentGameState.value!!.progress.isFinished()) return

        // gameState 바꾸기
        _currentGameState.value = _currentGameState.value!!.nextGameState(row, col)

        // history에 현재 상태 저장
        history.add(_currentGameState.value!!)
    }

    // 이전 GameState로 돌아가고 게임 진행 상태를 변경해주는 함수
    fun backTo(turn: Int) {
        history.subList(turn + 1, history.size).clear()
        _currentGameState.value = history.last()
    }

}