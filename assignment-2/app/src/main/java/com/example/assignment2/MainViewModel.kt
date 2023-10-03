package com.example.assignment2

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment2.databinding.ActivityMainBinding

class MainViewModel : ViewModel() {

    companion object {
        private val CASE_TO_WIN = setOf(
            setOf(0, 1, 2), setOf(3, 4, 5), setOf(6, 7, 8), // 가로.
            setOf(0, 3, 6), setOf(1, 4, 7), setOf(2, 5, 8), // 세로.
            setOf(0, 4, 8), setOf(2, 4, 6) // 대각선.
        )
    }

    private val gameHistoryRepository: GameHistoryRepository = GameHistoryRepository()

    private var _turnIndex: MutableLiveData<Int> = MutableLiveData(0)

    val turnIndex: LiveData<Int> = _turnIndex


    /**
     * [number] 영역 버튼을 클릭 이벤트 처리.
     *
     * @param number 클릭한 버튼 영역 숫자.
     */
    fun handleClickEvent(binding: ActivityMainBinding, textView: TextView): Int? {
        val number = getTickTacToeButtonNumber(binding, textView) ?: return null
        val currentGameInfo = gameHistoryRepository.get()
        // 게임 종료된 경우.
        if (currentGameInfo.gameStatus.isGameFinished()) {
            return null
        }
        val evenPlayerRecord = currentGameInfo.evenPlayerRecord
        val oddPlayerRecord = currentGameInfo.oddPlayerRecord
        // 이미 선택한 버튼인 경우.
        if (number in oddPlayerRecord || number in evenPlayerRecord) {
            return null
        }
        when (_turnIndex.value?.rem(2)) {
            0 -> {
                val newEvenPlayerRecord = evenPlayerRecord.plus(number)
                val newOddPlayerRecord = oddPlayerRecord.toSet()
                // 승리 여부 확인
                for (case in CASE_TO_WIN) {
                    if (newEvenPlayerRecord.containsAll(case)) {
                        gameHistoryRepository.add(
                            GameInfo(_turnIndex.value, GameStatus.EVEN_WIN, newEvenPlayerRecord, newOddPlayerRecord)
                        )
                        _turnIndex.value = _turnIndex.value?.plus(1)
                        return _turnIndex.value
                    }
                }
                // 무승부 확인
                if (newEvenPlayerRecord.size == 5) {
                    gameHistoryRepository.add(
                        GameInfo(_turnIndex.value, GameStatus.DRAW, newEvenPlayerRecord, newOddPlayerRecord)
                    )
                    _turnIndex.value = _turnIndex.value?.plus(1)
                    return _turnIndex.value
                }
                // 다음 차례 확인
                gameHistoryRepository.add(
                    GameInfo(_turnIndex.value, GameStatus.EVEN_TURN, newEvenPlayerRecord, newOddPlayerRecord)
                )
                _turnIndex.value = _turnIndex.value?.plus(1)
                return _turnIndex.value
            }

            1 -> {
                val newEvenPlayerRecord = evenPlayerRecord.toSet()
                val newOddPlayerRecord = oddPlayerRecord.plus(number)
                // 승리 여부 확인
                for (case in CASE_TO_WIN) {
                    if (newOddPlayerRecord.containsAll(case)) {
                        gameHistoryRepository.add(
                            GameInfo(_turnIndex.value, GameStatus.ODD_WIN, newEvenPlayerRecord, newOddPlayerRecord)
                        )
                        _turnIndex.value = _turnIndex.value?.plus(1)
                        return _turnIndex.value
                    }
                }
                // 다음 차례 확인
                gameHistoryRepository.add(
                    GameInfo(_turnIndex.value, GameStatus.ODD_TURN, newEvenPlayerRecord, newOddPlayerRecord)
                )
                _turnIndex.value = _turnIndex.value?.plus(1)
                return _turnIndex.value
            }

            else -> throw IllegalStateException("turnIndex cannot be null")
        }
    }

    fun reset() {
        gameHistoryRepository.clear()
        _turnIndex.value = 0
    }

    fun go(turnIndex: Int) {
        gameHistoryRepository.go(turnIndex)
        _turnIndex.value = turnIndex
    }

    fun getGameViewData(): GameViewData {
        val gameInfo = gameHistoryRepository.get()
        return gameInfo.toGameDataView()
    }

    fun getDrawerGameViewData(): GameHistoryData {
        val gameInfo = gameHistoryRepository.get()
        return if (gameInfo.gameStatus.isGameFinished()) {
            GameHistoryData.TypeGameOver(gameInfo.toDrawerGameDataView())
        } else {
            GameHistoryData.TypeGameTurn(gameInfo.toDrawerGameDataView())
        }
    }

    fun getAllDrawerGameViewData(): List<GameHistoryData> {
        val gameAllInfo = gameHistoryRepository.getAll()
        return gameAllInfo.filter {
            it.turnIndex != null && it.turnIndex > 0
        }.map {
            if (it.gameStatus.isGameFinished()) {
                GameHistoryData.TypeGameOver(it.toDrawerGameDataView())
            } else {
                GameHistoryData.TypeGameTurn(it.toDrawerGameDataView())
            }
        }
    }

    private fun getTickTacToeButtonNumber(binding: ActivityMainBinding, button: TextView): Int? = when (button) {
        binding.ticTacToe0 -> 0
        binding.ticTacToe1 -> 1
        binding.ticTacToe2 -> 2
        binding.ticTacToe3 -> 3
        binding.ticTacToe4 -> 4
        binding.ticTacToe5 -> 5
        binding.ticTacToe6 -> 6
        binding.ticTacToe7 -> 7
        binding.ticTacToe8 -> 8
        else -> null
    }

}