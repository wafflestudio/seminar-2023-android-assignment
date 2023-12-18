package com.wafflestudio.assignment5
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(modifier = Modifier.fillMaxSize())
        }
    }
}

enum class MenuList{
    Basic,
    Tutorial,
    Movie,
    Clock
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
){
    var showWhat by rememberSaveable { mutableStateOf(MenuList.Basic) }

    Surface(modifier){
        when (showWhat){
            MenuList.Basic -> MainMenu(
                  tutorialClick = { showWhat = MenuList.Tutorial },
                  movieClick = { showWhat = MenuList.Movie },
                  clockClick = { showWhat = MenuList.Clock }
                )

            MenuList.Tutorial -> Tutorial()
            MenuList.Movie -> Movie()
            MenuList.Clock -> Clock()
        }
    }
}

@Composable
fun MainMenu(
    modifier: Modifier = Modifier,
    tutorialClick: () -> Unit,
    movieClick:() -> Unit,
    clockClick:() -> Unit
){
    Column(
        modifier = modifier.fillMaxSize().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = tutorialClick
        ){
            Text("Tutorial")
        }

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = movieClick
        ){
            Text("Movie")
        }

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = clockClick
        ){
            Text("Clock")
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MainMenuPreview(){
    MainMenu(tutorialClick = {}, movieClick = {}, clockClick = {})
}