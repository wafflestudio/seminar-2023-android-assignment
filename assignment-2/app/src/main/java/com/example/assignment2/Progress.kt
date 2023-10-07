package com.example.assignment2

import android.graphics.Color

// Progress는 현재 게임의 진행 상태를 나타낸다.
enum class Progress {
    OPLAYING, XPLAYING,  OWINS, XWINS, DRAW, NULL;

    // 현재 상태에 대한 information
    override fun toString(): String {
        return when(this) {
            OPLAYING -> "O의 차례입니다"
            XPLAYING -> "X의 차례입니다"
            OWINS -> "O의 승리!!"
            XWINS -> "X의 승리!!"
            DRAW -> "무승부"
            NULL -> "아직 착수되지 않은 상태" // This will not be displayed.
        }
    }

    // 현재 progress의 내용에 해당하는 symbol
    fun symbol(): String {
        return when (this) {
            OPLAYING -> "O"
            XPLAYING -> "X"
            else -> ""
        }
    }

    // 현재 progress의 내용에 해당하는 color
    fun color(): Int {
        return when (this) {
            OPLAYING -> Color.parseColor("#2A27FF")
            XPLAYING -> Color.parseColor("#FF3134")
            else -> Color.parseColor("#FFFFFF")
        }
    }

    // 게임이 종료됐는지 알려주는 함수
    fun isFinished(): Boolean {
        return when (this) {
            OPLAYING -> false
            XPLAYING -> false
            NULL -> false
            else -> true
        }
    }

}