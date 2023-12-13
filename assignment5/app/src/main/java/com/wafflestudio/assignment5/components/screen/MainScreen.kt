package com.wafflestudio.assignment5.components.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wafflestudio.assignment5.components.compose.main.EntryButton

@Composable
fun MainScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    Column {
        EntryButton(modifier = Modifier, "튜토리얼 실행하기", onClick = { onClick("Tutorial") })
        EntryButton(modifier = Modifier, "영화 검색하기", onClick = { onClick("MovieSearch") })
        EntryButton(modifier = Modifier, "디지털 시계 보기", onClick = { onClick("DigitClock") })
    }
}