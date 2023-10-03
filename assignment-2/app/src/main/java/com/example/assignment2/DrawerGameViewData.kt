package com.example.assignment2

import androidx.annotation.DrawableRes

data class DrawerGameViewData(
    val turnIndex: Int?,
    val playground: Map<Int, String>,
    val gameStatus: String,
    @DrawableRes val imageRes: Int,
)
