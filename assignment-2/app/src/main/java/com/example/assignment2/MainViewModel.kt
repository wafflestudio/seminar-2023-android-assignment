package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Transformations

class MainViewModel : ViewModel() {

    private val _board: MutableLiveData<List<Cell>> = MutableLiveData(InitialBoardState)

    private fun isGameOver(board: List<Cell>): Boolean {
        return EndList.any { (a, b, c) ->
            board[a] != Cell.EMPTY && board[a] == board[b] && board[b] == board[c]
        }
    }
    val board: LiveData<List<Cell>> get() = _board

    var history: MutableList<List<Cell>> = mutableListOf(InitialBoardState)

    val gameState: LiveData<Game> = Transformations.map(_board) { board ->
        when {
            isGameOver(board) -> Game.Over
            board.count { it != Cell.EMPTY } == 9 -> Game.Draw
            board.count { it != Cell.EMPTY } % 2 == 0 -> Game.O
            else -> Game.X
        }
    }


    fun handleClick(id: Int): Boolean {
        val currentCellState = _board.value?.get(id) ?: return false

        val currentTurn = getCurrentTurn(gameState.value) ?: return false

        if (currentCellState != Cell.EMPTY) {
            return false
        }

        val updatedBoard = _board.value?.toMutableList()
        updatedBoard?.set(id, currentTurn)

        _board.value = updatedBoard
        history.add(updatedBoard!!)

        return true
    }

    private fun getCurrentTurn(state: Game?): Cell? {
        return when (state) {
            Game.O -> Cell.O
            Game.X -> Cell.X
            else -> null
        }
    }


    fun retry() {
        clearHistoryFrom(1)
        _board.value = InitialBoardState
    }

    fun goToPast(position: Int, boardSnapshot: List<Cell>) {
        updateBoard(boardSnapshot)
        clearHistoryFrom(position)
    }

    private fun clearHistoryFrom(start: Int) {
        history.subList(start, history.size).run { clear() }
    }

    private fun updateBoard(boardSnapshot: List<Cell>) {
        _board.value = boardSnapshot
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
        val InitialBoardState = List(9) { Cell.EMPTY }
    }
}