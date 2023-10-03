package com.example.assignment2

enum class GameStatus {
    START,
    EVEN_TURN,
    ODD_TURN,
    EVEN_WIN,
    ODD_WIN,
    DRAW;

    fun isGameFinished(): Boolean = when (this) {
        DRAW, ODD_WIN, EVEN_WIN -> true
        else -> false
    }

    fun getTitleText(): String = when (this) {
        START -> "게임 시작!"
        EVEN_TURN -> "O의 차례입니다"
        ODD_TURN -> "X의 차례입니다"
        DRAW -> "무승부"
        EVEN_WIN -> "O의 승리!!"
        ODD_WIN -> "X의 승리!!"
    }

    fun getDrawableRes(): Int = when (this) {
        EVEN_TURN, ODD_TURN -> R.drawable.rounded_rectangle
        EVEN_WIN -> R.drawable.rounded_rectangle_even_win
        ODD_WIN -> R.drawable.rounded_rectangle_odd_win
        DRAW -> R.drawable.rounded_rectangle_draw
        else -> throw IllegalStateException("Not valid Game tatus")
    }

}