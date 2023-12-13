package com.wafflestudio.assignment5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wafflestudio.assignment5.components.screen.DigitClock
import com.wafflestudio.assignment5.components.screen.MainScreen
import com.wafflestudio.assignment5.components.screen.MovieSearch
import com.wafflestudio.assignment5.components.screen.Tutorial
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(Modifier.fillMaxSize())
        }
    }

    @Composable
    private fun MyApp(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = "MainScreen",
    )
    {
        NavHost(navController = navController, startDestination = startDestination){
            composable("MainScreen") { MainScreen { destination ->
                when (destination) {
                    "Tutorial" -> navController.navigate("Tutorial")
                    "MovieSearch" -> navController.navigate("MovieSearch")
                    "DigitClock" -> navController.navigate("DigitClock")
                }
            } }
            composable("Tutorial") { Tutorial(modifier = Modifier) }
            composable("MovieSearch") { MovieSearch(modifier = Modifier) }
            composable("DigitClock"){ DigitClock(modifier = Modifier) }
        }
    }
}