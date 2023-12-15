package com.wafflestudio.assignment5.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wafflestudio.assignment5.utils.ToneGenerator
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.pow
import kotlin.math.roundToInt

@Composable
fun MusicPage() {
    val player = remember { ToneGenerator() }
    var answer by remember { mutableStateOf(generateRandomFrequency()) }
    var input by remember { mutableStateOf((Const.LOW + Const.HIGH) / 2) }
    var showAnswer by remember { mutableStateOf(false) }
    val gye = remember {
        listOf("라", "라#", "시", "도", "도#", "레", "레#", "미", "파", "파#", "솔", "솔#", "라")
    }
    var width by remember {
        mutableStateOf(0.0)
    }
    var pointDragAmount by remember {
        mutableStateOf(0.0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(7.dp),
    ) {
        BlackButton(text = "문제 재생") { player.play(answer) }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0..12) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = gye[i], fontSize = 15.sp)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    width = it.size.width.toDouble()
                }, horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0..12) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .background(Color.Black)
                            .size(height = 10.dp, width = 2.dp)
                    )
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .offset { IntOffset(pointDragAmount.roundToInt(), 0) }
                .pointerInput(Unit) {
                    this.detectDragGestures { change, dragAmount ->
                        change.consume()
                        if (showAnswer.not()) {
                            pointDragAmount += (dragAmount.x)
                            pointDragAmount = pointDragAmount.coerceIn(-width / 2, width / 2)
                            input = Const.LOW * 2.0.pow((pointDragAmount + width / 2) / width)
                        }
                    }
                }) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .size(height = 10.dp, width = 2.dp)
            )
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .size(height = 60.dp, width = 60.dp)
            )
        }
        BlackButton(text = "${input.round2()} Hz") {}
        BlackButton(text = "정답 보기") { showAnswer = true }
        if (showAnswer) {
            BlackButton(text = "정답 : ${answer.round2()}Hz\n오차율 : ${getDiff(input, answer)}%") {
                showAnswer = true
            }
            BlackButton(text = "다시") {
                showAnswer = false
                pointDragAmount = 0.0
                answer = generateRandomFrequency()
            }
        }


    }
}

fun getDiff(a: Double, b: Double): Double {
    return (abs((log2(a / Const.LOW) - log2(b / Const.LOW)) / log2(b / Const.LOW)) * 100.0).round2()
}

fun generateRandomFrequency(): Double {
    return (Math.random() * (Const.HIGH - Const.LOW)) + Const.LOW
}

@Composable
private fun BlackButton(
    text: String,
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .background(Color.Black, shape = RoundedCornerShape(20.dp))
        .padding(15.dp)
        .clickable {
            onClick()
        }) {
        Text(
            text = text, color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold
        )
    }
}

fun Double.round2(): Double = String.format("%.02f", this).toDouble()

object Const {
    const val LOW = 440.0
    const val HIGH = 220.0
}