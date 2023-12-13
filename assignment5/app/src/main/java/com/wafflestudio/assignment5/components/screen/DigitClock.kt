package com.wafflestudio.assignment5.components.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wafflestudio.assignment5.components.compose.clock.DigitalBCD
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DigitClock(modifier: Modifier) {
    var currentTime by remember { mutableStateOf(Date()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000) // Update every second
            currentTime = Date()
        }
    }

    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    val formattedTime = remember(currentTime) {
        timeFormat.format(currentTime)
    }
    val digitList = formattedTime.filter { it.isDigit() }.map { it.toString().toInt() }
    Log.d("digitclock", "$digitList")

    Row (modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
//        for (digit in digitList) {
//            DigitalBCD(modifier = Modifier.weight(1f), bcds = getSegmentsForDigit(digit))
//            Log.d("check", "digit: $digit")
//        }
//        Spacer(modifier = Modifier.width(8.dp))
        for (digit in digitList) {
            Box (modifier = Modifier.weight(0.6f)) {
                DigitalBCD(modifier = Modifier, bcds = getSegmentsForDigit(digit))
            }
        }

    }

}

fun getSegmentsForDigit(digit: Int): List<Boolean> {
    return when (digit) {
        0 -> listOf(true, true, true, false, true, true, true)
        1 -> listOf(false, false, true, false, false, true, false)
        2 -> listOf(true, false, true, true, true, false, true)
        3 -> listOf(true, false, true, true, false, true, true)
        4 -> listOf(false, true, true, true, false, true, false)
        5 -> listOf(true, true, false, true, false, true, true)
        6 -> listOf(true, true, false, true, true, true, true)
        7 -> listOf(true, false, true, false, false, true, false)
        8 -> listOf(true, true, true, true, true, true, true)
        9 -> listOf(true, true, true, true, false, true, true)
        else -> listOf(false, false, false, false, false, false, false)
    }
}