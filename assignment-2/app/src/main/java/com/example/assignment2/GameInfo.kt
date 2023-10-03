package com.example.assignment2

data class GameInfo(
    val turnIndex: Int?,
    /**
     * 게임 상태. [GameStatus].
     */
    val gameStatus: GameStatus,
    /**
     * 짝수 번째 player (O player) 기록.
     */
    val evenPlayerRecord: Set<Int>,
    /**
     * 홀수 번째 player (X player) 기록.
     */
    val oddPlayerRecord: Set<Int>,
)

fun GameInfo.toGameDataView(): GameViewData {
    val titleText = this.gameStatus.getTitleText()
    val resetText = if (this.gameStatus.isGameFinished()) {
        "한판 더"
    } else {
        "초기화"
    }
    val playground = HashMap<Int, String>()
    this.evenPlayerRecord.forEach { num ->
        playground[num] = "O"
    }
    this.oddPlayerRecord.forEach { num ->
        playground[num] = "X"
    }
    return GameViewData(
        titleText,
        resetText,
        playground
    )
}

fun GameInfo.toDrawerGameDataView(): DrawerGameViewData {
    val playground = HashMap<Int, String>()
    this.evenPlayerRecord.forEach { num ->
        playground[num] = "O"
    }
    this.oddPlayerRecord.forEach { num ->
        playground[num] = "X"
    }
    return DrawerGameViewData(
        turnIndex?.plus(1),
        playground,
        gameStatus.getTitleText(),
        gameStatus.getDrawableRes(),
    )
}