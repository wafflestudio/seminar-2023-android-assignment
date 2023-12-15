package com.example.jet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.time.LocalTime

@Composable
fun Clock(){
    var currentTimeDigits= remember {
        mutableStateOf(getTimeDigitList())
    }
    var counter by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = counter){
        Log.d("aaaa","before delayed")
        delay(500)
        Log.d("aaaa","after delayed")
        currentTimeDigits.value=getTimeDigitList()
        counter++
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        SetDigit(currentTimeDigits.value[0])
        SetDigit(currentTimeDigits.value[1])
        Text(":", fontSize = 76.sp, color = Color.Black)
        SetDigit(currentTimeDigits.value[2])
        SetDigit(currentTimeDigits.value[3])
        Text(":", fontSize = 76.sp, color = Color.Black)
        SetDigit(currentTimeDigits.value[4])
        SetDigit(currentTimeDigits.value[5])
    }
}



@Composable
fun SetDigit(digit:Int){
    val parts= getDigitPart(digit)
    Box(modifier = Modifier
        .width(56.dp)
        .height(76.dp)
        .padding(start = 8.dp, end = 8.dp)
    ){
        Box(modifier = (Modifier
            .width(32.dp)
            .height(4.dp)
            .offset(4.dp, 0.dp)).background(parts[0]))
        Box(modifier = (Modifier
            .width(4.dp)
            .height(32.dp)
            .offset(0.dp, 4.dp)).background(parts[1]))
        Box(modifier = (Modifier
            .width(4.dp)
            .height(32.dp)
            .offset(36.dp, 4.dp)).background(parts[2]))
        Box(modifier = (Modifier
            .width(32.dp)
            .height(4.dp)
            .offset(4.dp, 36.dp)).background(parts[3]))
        Box(modifier = (Modifier
            .width(4.dp)
            .height(32.dp)
            .offset(0.dp, 40.dp)).background(parts[4]))
        Box(modifier = (Modifier
            .width(4.dp)
            .height(32.dp)
            .offset(36.dp, 40.dp)).background(parts[5]))
        Box(modifier = (Modifier
            .width(32.dp)
            .height(4.dp)
            .offset(4.dp, 72.dp)).background(parts[6]))
    }
}


fun getTimeDigitList(): List<Int> {
    var currentTime: LocalTime = LocalTime.now().plusHours(9)//GMT+9
    /*
    var counter by remember { mutableStateOf(0) }
    LaunchedEffect(key1 = counter){
        Log.d("aaaa","before delayed")
        delay(1000)
        Log.d("aaaa","after delayed")
        currentTime=LocalTime.now().plusHours(9)//GMT+9
        counter++
    }*/
    Log.d("aaaa",currentTime.toString())
    return listOf(
            currentTime.hour / 10,
            currentTime.hour % 10,
            currentTime.minute / 10,
            currentTime.minute % 10,
            currentTime.second / 10,
            currentTime.second % 10,
        )

}
fun getDigitPart(digit:Int):List<Color>{
    val myGray=Color(0xFFDDDDDD)
    val parts= mutableListOf(myGray,myGray,myGray,myGray,myGray,myGray,myGray)
    when(digit){
        0-> listOf(0,1,2,4,5,6)
        1-> listOf(2,5)
        2-> listOf(0,2,3,4,6)
        3-> listOf(0,2,3,5,6)
        4-> listOf(1,2,3,5)
        5-> listOf(0,1,3,5,6)
        6-> listOf(0,1,3,4,5,6)
        7-> listOf(0,1,2,5)
        8-> listOf(0,1,2,3,4,5,6)
        9-> listOf(0,1,2,3,5,6)
        else-> listOf()
    }.forEach {
        parts[it]=Color.Black
    }
    return parts
}