package com.wafflestudio.assignment5

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(),color = MaterialTheme.colorScheme.background) {
                    MessageCard(Message("Android","JC"))
                }
            }
        }
        */
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    MyAppNavHost(
                        navController = navController
                    )
                }
            }
        }
    }
}

data class Message(val author:String,val body:String)

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "Intro"
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable("Intro"){
            IntroScreen(
                onNavigateToTutorial = {
                    navController.navigate("Tutorial")
                },
                onNavigateToSearch = {
                    navController.navigate("Search")
                },
                onNavigateToClock = {
                    navController.navigate("clock")
                }
            )
        }
        composable("Tutorial"){
            TutorialScreen()
        }
        composable("Search"){
            SearchScreen()
        }
        composable("Clock"){
            ClockScreen()
        }
    }
}

@Preview
@Composable
fun IntroScreen(
    onNavigateToTutorial: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToClock: () -> Unit = {}
){
    MaterialTheme{
        Surface{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ){
                Button(
                    onClick = onNavigateToTutorial,
                    colors = ButtonDefaults.buttonColors(Color.DarkGray),
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                ){
                    Text(
                        text = "튜토리얼",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = onNavigateToSearch,
                    colors = ButtonDefaults.buttonColors(Color.DarkGray),
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                ){
                    Text(
                        text = "영화 검색",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = onNavigateToClock,
                    colors = ButtonDefaults.buttonColors(Color.DarkGray),
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp)
                ){
                    Text(
                        text = "디지털 시계",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun TutorialScreen(){
    MaterialTheme {
        Surface {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text("abc")
            }
        }
    }
}

@Preview
@Composable
fun SearchScreen(){
    MaterialTheme {
        Surface {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text("abcd")
            }
        }
    }
}

@Preview
@Composable
fun ClockScreen(){

}

@Composable
fun MessageCard(msg:Message){
    Row(modifier = Modifier.padding(all=30.dp)){
        Text(
            text = "aaa",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.width(15.dp))

        var isExpanded by remember{ mutableStateOf(false) }

        Column (modifier = Modifier.clickable {isExpanded=!isExpanded}){
            Text(text = msg.author)
            Spacer(modifier = Modifier.height(5.dp))
            Surface (
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 5.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ){
                Text(
                    text = msg.body.repeat(6),
                    modifier = Modifier.padding(all=4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun PreviewMessageCard(name:String="aa"){
    MaterialTheme {
        Surface {
            MessageCard(msg = Message("Colleague","Hey"))
        }
    }
}

@Composable
fun Conversation(messages:List<Message>){
    LazyColumn{
        items(messages){
                message -> MessageCard(msg = message)
        }
    }
}

@Composable
fun PreviewConversation(){
    MaterialTheme {
        Conversation(messages = listOf(Message("a", "1"),
            Message("B", "2"),
            Message("c", "3"),
            Message("d", "4"),
            Message("e", "5"),
            Message("f", "6"),
            Message("g", "7"),
            Message("h", "812312323"),
            Message("i", "9"),
            Message("j", "0")))
    }
}