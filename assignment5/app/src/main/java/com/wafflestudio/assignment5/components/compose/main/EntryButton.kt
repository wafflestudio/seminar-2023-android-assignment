package com.wafflestudio.assignment5.components.compose.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun EntryButton (modifier: Modifier = Modifier,
                 text: String,
                 onClick: () -> Unit,
                 buttonColor: Color = MaterialTheme.colors.primary,
                 textColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.button
        )
    }
}
