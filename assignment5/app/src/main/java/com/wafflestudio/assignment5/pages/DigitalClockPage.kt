package com.wafflestudio.assignment5.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.time.LocalTime

object Config {
    const val HEIGHT = 30
    const val STROKE = 3
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DigitalClockPage() {
    Column {
        MyDigitalClock(
            initHour = LocalTime.now().hour,
            initMinute = LocalTime.now().minute,
            initSecond = LocalTime.now().second
        )
    }
}

@Composable
fun MyDigitalClock(
    initHour: Int,
    initMinute: Int,
    initSecond: Int,
) {
    var hour by remember {
        mutableIntStateOf(initHour)
    }
    var minute by remember {
        mutableIntStateOf(initMinute)
    }
    var second by remember {
        mutableIntStateOf(initSecond)
    }

    LaunchedEffect(Unit) {
        while (true) {
            for (min in 0..59) {
                for (sec in 0..59) {
                    delay(1000L)
                    second++
                    second %= 60
                }
                minute++
                minute %= 60
            }
            hour++
            hour %= 60
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            BCDSegment(hour / 10)
            BCDSegment(hour % 10)
            Colon()
            BCDSegment(minute / 10)
            BCDSegment(minute % 10)
            Colon()
            BCDSegment(second / 10)
            BCDSegment(second % 10)
        }
    }
}

@Composable
fun BCDSegment(
    num: Int,
) {
    val input = remember(num) {
        InputData.cycle[num]!!
    }
    Box(
        modifier = Modifier.size(
            width = (Config.HEIGHT).dp, height = (Config.HEIGHT * 2 - Config.STROKE).dp
        )
    ) {
        Ve(alignment = Alignment.TopEnd, on = input[0])
        Ve(alignment = Alignment.BottomEnd, on = input[1])
        Ho(alignment = Alignment.BottomCenter, on = input[2])
        Ve(alignment = Alignment.BottomStart, on = input[3])
        Ve(alignment = Alignment.TopStart, on = input[4])
        Ho(alignment = Alignment.TopCenter, on = input[5])
        Ho(alignment = Alignment.Center, on = input[6])
    }
}

@Composable
fun Ve(
    on: Boolean = true,
    alignment: Alignment,
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .height(Config.HEIGHT.dp)
                .width(Config.STROKE.dp)
                .padding(vertical = Config.STROKE.dp)
                .background(if (on) Color.Black else Color.LightGray)
        )
    }
}

@Composable
fun Ho(
    on: Boolean = true,
    alignment: Alignment,
) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = alignment
    ) {
        Box(
            modifier = Modifier
                .width(Config.HEIGHT.dp)
                .height(Config.STROKE.dp)
                .padding(horizontal = Config.STROKE.dp)
                .background(if (on) Color.Black else Color.LightGray)
        )
    }
}

@Composable
fun Colon() {
    Column(
        modifier = Modifier
            .width(Config.STROKE.dp)
            .height(Config.HEIGHT.dp * 2 + Config.STROKE.dp * 3),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(Config.STROKE.dp)
                .background(Color.Black)
        )
        Box(
            modifier = Modifier
                .size(Config.STROKE.dp)
                .background(Color.Black)
        )
    }
}

object InputData {
    val cycle = mapOf(
        0 to listOf(true, true, true, true, true, true, false),
        1 to listOf(true, true, false, false, false, false, false),
        2 to listOf(true, false, true, true, false, true, true),
        3 to listOf(true, true, true, false, false, true, true),
        4 to listOf(true, true, false, false, true, false, true),
        5 to listOf(false, true, true, false, true, true, true),
        6 to listOf(false, true, true, true, true, true, true),
        7 to listOf(true, true, false, false, false, true, false),
        8 to listOf(true, true, true, true, true, true, true),
        9 to listOf(true, true, true, false, true, true, true),
    )
}