package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class MainViewModel : ViewModel() {

    private val _board: MutableLiveData<List<CellState>> = MutableLiveData(InitialBoardState)
    val board: LiveData<List<CellState>> = _board

    var history: MutableList<List<CellState>> = mutableListOf(InitialBoardState)

    val gameState: LiveData<GameState> = _board.map { board ->
        if (isGameOver(board)) GameState.Over
        else if (board.count { it != CellState.E } == 9) GameState.Draw
        else if (board.count { it != CellState.E } % 2 == 0) GameState.O
        else GameState.X
    }

    fun handleClick(id: Int): Boolean {
        val beforeClick = _board.value?.get(id) ?: return false
        val turn: CellState = when (gameState.value) {
            GameState.O -> CellState.O
            GameState.X -> CellState.X
            else -> return false
        }

        if (beforeClick == CellState.E) {
            val boardBefore = _board.value?.toMutableList() ?: return false
            _board.value = boardBefore.apply {
                set(id, turn)
            }.also {
                history.add(it)
            }
            return true
        }
        return false
    }

    fun retry() {
        history.subList(1, history.size).clear()
        _board.value = InitialBoardState
    }

    fun goToPast(removeStart: Int, board: List<CellState>) {
        _board.value = board

        history.subList(removeStart, history.size).clear()
    }

    companion object {
        val EndList = listOf(
            Triple(0, 1, 2),
            Triple(3, 4, 5),
            Triple(6, 7, 8),
            Triple(0, 3, 6),
            Triple(1, 4, 7),
            Triple(2, 5, 8),
            Triple(0, 4, 8),
            Triple(2, 4, 6),
        )
        val InitialBoardState = List(9) { CellState.E }
    }
}