package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _board: MutableLiveData<List<CellState>> = MutableLiveData(InitialBoardState)
    val board: LiveData<List<CellState>> = _board

    val history: MutableList<List<CellState>> = mutableListOf(InitialBoardState)

    fun handleClick(id: Int): Boolean {
        if (board.value!![id] != CellState.E) return false

        val gameState = getGameStateFromBoard(_board.value!!)
        if (gameState == GameState.Draw || gameState == GameState.Over) return false

        val newBoard = _board.value!!.toMutableList().apply {
            if (gameState == GameState.O) set(id, CellState.O)
            else set(id, CellState.X)
        }
        _board.value = newBoard
        history.add(newBoard)
        return true
    }

    fun retry(): Int {
        val removed = history.size - 1
        history.subList(1, history.size).clear()
        _board.value = InitialBoardState
        return removed
    }

    fun goToPast(removeStart: Int) {
        history.subList(removeStart, history.size).clear()
        _board.value = history.last()
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