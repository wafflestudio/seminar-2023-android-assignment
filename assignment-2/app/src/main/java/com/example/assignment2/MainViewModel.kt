package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        private val CASE_TO_WIN = setOf(
            setOf(0, 1, 2), setOf(3, 4, 5), setOf(6, 7, 8), // 가로.
            setOf(0, 3, 6), setOf(1, 4, 7), setOf(2, 5, 8), // 세로.
            setOf(0, 4, 8), setOf(2, 4, 6) // 대각선.
        )
    }

    private var turnIndex: Int = 0

    /**
     * 게임 상태. [GameStatus].
     */
    private val _gameStatus: MutableLiveData<GameStatus> = MutableLiveData(GameStatus.START)

    val gameStatus: LiveData<GameStatus> = _gameStatus

    /**
     * 짝수 번째 player (O player) 기록.
     */
    private val evenPlayerRecord: MutableSet<Int> = mutableSetOf()

    /**
     * 홀수 번째 player (X player) 기록.
     */
    private val oddPlayerRecord: MutableSet<Int> = mutableSetOf()

    /**
     * [number] 영역 버튼을 클릭 이벤트 처리.
     *
     * @param number 클릭한 버튼 영역 숫자.
     */
    fun handleClickEvent(number: Int): String? {
        // 이미 선택한 버튼인 경우.
        if (number in oddPlayerRecord || number in evenPlayerRecord) {
            return null
        }
        val turnText = when (turnIndex % 2) {
            0 -> "O"
            1 -> "X"
            else -> ""
        }
        if (turnText == "O") { // 짝수 player 차례
            evenPlayerRecord.add(number)
            for (case in CASE_TO_WIN) {
                if (evenPlayerRecord.containsAll(case)) {
                    _gameStatus.value = GameStatus.GAME_OVER
                    return turnText
                }
            }
        } else { // 홀수 player 차례
            oddPlayerRecord.add(number)
            for (case in CASE_TO_WIN) {
                if (oddPlayerRecord.containsAll(case)) {
                    _gameStatus.value = GameStatus.GAME_OVER
                    return turnText
                }
            }
        }
        if (evenPlayerRecord.size == 5) { // 무승부
            _gameStatus.value = GameStatus.DRAW
            return turnText
        }
        turnIndex++
        _gameStatus.value = if (turnIndex % 2 == 0) { // 차례 넘기기
            GameStatus.EVEN_TURN
        } else {
            GameStatus.ODD_TURN
        }
        return turnText
    }

    fun resetViewModel() {
        turnIndex = 0
        _gameStatus.value = GameStatus.START
        evenPlayerRecord.clear()
        oddPlayerRecord.clear()
    }

}