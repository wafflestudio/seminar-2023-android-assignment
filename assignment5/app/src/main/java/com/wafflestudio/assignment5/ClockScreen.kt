package com.wafflestudio.assignment5

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ClockScreen(
    modifier: Modifier = Modifier,
){

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun ClockPreview(){
    MyApplicationTheme {
        ClockScreen()
    }
}