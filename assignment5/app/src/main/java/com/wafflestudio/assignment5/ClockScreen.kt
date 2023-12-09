package com.wafflestudio.assignment5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat

@Composable
fun ClockScreen(
    modifier: Modifier = Modifier,
) {
    var hour by rememberSaveable { mutableStateOf(0) }
    var minute by rememberSaveable { mutableStateOf(0) }
    var second by rememberSaveable { mutableStateOf(0) }
    val digits : List<@Composable () -> Unit> =
        listOf({ Zero() }, { One() }, { Two() }, { Three()}, { Four()},
            { Five() }, { Six()}, { Seven()}, { Eight()}, { Nine()})
    val timeByDigits: Array<@Composable () -> Unit> = arrayOf({ Zero() }, { One() }, { Two() }, { Three()}, { Four()},
        { Five() })
    var now: Long = System.currentTimeMillis()

    LaunchedEffect(now){
        while(true) {
            hour = (SimpleDateFormat("HH").format(now).toInt()+9) % 24 //코리안 타임
            minute = SimpleDateFormat("mm").format(now).toInt()
            second = SimpleDateFormat("ss").format(now).toInt()
            delay(1000L)
            now += 1000
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ){
        timeByDigits[0] = digits[hour / 10]
        timeByDigits[1] = digits[hour % 10]
        timeByDigits[2] = digits[minute / 10]
        timeByDigits[3] = digits[minute % 10]
        timeByDigits[4] = digits[second / 10]
        timeByDigits[5] = digits[second % 10]

        timeByDigits[0].invoke()
        timeByDigits[1].invoke()
        Dots()
        timeByDigits[2].invoke()
        timeByDigits[3].invoke()
        Dots()
        timeByDigits[4].invoke()
        timeByDigits[5].invoke()
    }
}

