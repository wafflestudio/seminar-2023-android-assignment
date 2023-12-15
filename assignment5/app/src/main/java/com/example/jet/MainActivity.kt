package com.example.jet

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.tutorial.SampleData
import com.example.jet.ui.theme.JetTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.lazy.items
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel:MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "MainAc") {
                composable("MainAc") { MainAc(navController) }
                composable("Tutorial") { Tutorial(/*...*/) }
                composable("Movie") { Movie(/*...*/) }
                composable("Clock") { Clock(/*...*/) }
                /*...*/
            }
            //MainAc(navController)
        }

        viewModel.error.observe(this) { eventWrapper ->
            eventWrapper.getContentIfNotHandled()?.let {
                Log.d("aaaa","ㄷㄱ객 ㅐㅊ쳣")
                Toast.makeText(this,"error occur",Toast.LENGTH_SHORT).show()
            }
        }


    }
}

@Composable
fun MainButton(name:String,route:String,navController: NavController){


    Box(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .padding(15.dp)
        .clip(RoundedCornerShape(8.dp))
        .border(3.dp, color = Color.Black)// 모서리 둥글게 설정
        .background(color = Color.White)
        .clickable {
            Log.d("aaaa", "clicked")
            navController.navigate(route) {
                //popUpTo("MainAc"){ inclusive = true }
                Log.d("aaaa", navController.currentBackStackEntry?.destination?.route.toString())
            }
        },
        contentAlignment = Alignment.Center


    ){
        Text(
            text=name,
            fontSize = 30.sp,
        )
    }
}
@Composable
fun MainAc(navController: NavController){
    //Text("das")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(5.dp, color = Color.Cyan)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center

    ) {
        MainButton("Tutorial","Tutorial",navController)
        MainButton("Movie(LOCKED)","Movie",navController)
        MainButton("Clock","Clock",navController)
    }
}