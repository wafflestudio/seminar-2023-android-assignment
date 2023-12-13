package com.wafflestudio.assignment5.components.compose.clock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DigitalBCD(modifier: Modifier, bcds: List<Boolean>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Top segment
        HorizontalSegment(bcds[0])

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Left-top and right-top segments
            VerticalSegment(bcds[1])
            Spacer(modifier = Modifier.width(12.dp))
            VerticalSegment(bcds[2])
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            HorizontalSegment(bcds[3])
        }

        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Left-bottom and right-bottom segments
            VerticalSegment(bcds[4])
            Spacer(modifier = Modifier.width(12.dp))
            VerticalSegment(bcds[5])
        }

        // Bottom segment
        HorizontalSegment(bcds[6])
    }
}

@Composable
fun HorizontalSegment(on: Boolean) {
    Box(
        modifier = Modifier
            .height(8.dp)
            .width(28.dp)
            .background(color = if (on) Color.Black else Color.LightGray)
    )
}

@Composable
fun VerticalSegment(on: Boolean) {
    Box(
        modifier = Modifier
            .height(28.dp)
            .width(8.dp)
            .background(color = if (on) Color.Black else Color.LightGray)
    )
}