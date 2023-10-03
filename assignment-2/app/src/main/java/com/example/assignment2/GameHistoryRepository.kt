package com.example.assignment2

import android.util.Log

class GameHistoryRepository {

    private var gameHistory: MutableList<GameInfo> = mutableListOf()

    fun add(newGameInfo: GameInfo) {
        Log.d("hello", newGameInfo.toString())
        gameHistory.add(newGameInfo)
    }

    fun get(): GameInfo {
        return runCatching { gameHistory.last() }.getOrDefault(
            GameInfo(-1, GameStatus.START, emptySet(), emptySet())
        )
    }

    fun getAll(): List<GameInfo> {
        return gameHistory
    }

    fun go(turnIndex: Int) {
        gameHistory = gameHistory.subList(0, turnIndex)
    }

    fun clear() {
        gameHistory.clear()
    }

}
