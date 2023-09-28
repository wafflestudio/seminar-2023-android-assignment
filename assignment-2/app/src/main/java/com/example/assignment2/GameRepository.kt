package com.example.assignment2

class GameRepository {

}

data class GameInfo(
    val turnIndex: Int,
    val gameStatus: GameStatus,
    val evenPlayerRecord: Set<Int>,
    val oddPlayerRecord: Set<Int>,
)