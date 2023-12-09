package com.wafflestudio.assignment5

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Dots(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.height(100.dp).width(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .height(5.dp)
                .width(5.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
                modifier = Modifier
                    .height(5.dp)
                    .width(5.dp)
                    .background(Color.Black)
                    .align(Alignment.CenterHorizontally),
        )

    }
}
@Preview(showBackground = true)
@Composable
fun Zero(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Gray)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun One(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Gray)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Gray)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Two(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Three(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Four(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Gray)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Gray)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Five(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Six(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Seven(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Gray)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Gray)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Eight(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Nine(
    modifier : Modifier = Modifier,
){
    val length = 30
    val width = 5
    return Column(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(width.dp)
                .width(length.dp)
                .background(Color.Black)
                .align(Alignment.CenterHorizontally),
        )
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
        Row(
            modifier = Modifier
                .height(35.dp)
                .width(40.dp)
        ){
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Gray)
            )
            Box(
                modifier = Modifier
                    .height(width.dp)
                    .width(length.dp)
                    .background(Color.Black)
                    .align(Alignment.Bottom),
            )
            Box(
                modifier = Modifier
                    .height(length.dp)
                    .width(width.dp)
                    .background(Color.Black)
            )
        }
    }
}