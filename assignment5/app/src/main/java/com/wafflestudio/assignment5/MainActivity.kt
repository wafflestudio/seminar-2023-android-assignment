package com.wafflestudio.assignment5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wafflestudio.assignment5.pages.DigitalClockPage
import com.wafflestudio.assignment5.pages.MoviePage
import com.wafflestudio.assignment5.pages.MovieReviewPage
import com.wafflestudio.assignment5.pages.MusicPage
import com.wafflestudio.assignment5.pages.TutorialPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetNavigation()
        }
    }

    @Composable
    private fun SetNavigation() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = NavigationDestination.Intro) {
            composable(NavigationDestination.Intro) {
                IntroPage(onEnter = { navController.navigate(it) })
            }
            composable(NavigationDestination.Tutorial) { TutorialPage() }
            composable(NavigationDestination.Movie) {
                MoviePage {
                    navController.navigate("${NavigationDestination.MovieReview}/${it.id}")
                }
            }
            composable(NavigationDestination.Clock) { DigitalClockPage() }
            composable(
                "${NavigationDestination.MovieReview}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) {
                MovieReviewPage(it.arguments?.getInt("movieId") ?: 1)
            }
            composable(NavigationDestination.Music) {
                MusicPage()
            }
        }
    }
}