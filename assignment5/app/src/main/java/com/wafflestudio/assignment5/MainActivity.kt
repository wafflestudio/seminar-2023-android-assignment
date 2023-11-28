package com.wafflestudio.assignment5

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.West
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
            var boardshow by rememberSaveable{ mutableStateOf(false) }
            if(boardshow) {
                Boards()
            }
            else{
                TutorialScreen(
                    onContinueClicked = { boardshow = true }
                )
            }
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
        Surface(
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ){
                IntroButton(content = "튜토리얼", onclick = onNavigateToTutorial)
                Spacer(modifier = Modifier.height(15.dp))
                IntroButton(content = "영화 검색", onclick = onNavigateToSearch)
                Spacer(modifier = Modifier.height(15.dp))
                IntroButton(content = "디지털 시계", onclick = onNavigateToClock)
            }
        }
    }
}

@Composable
fun IntroButton(
    content:String="",
    onclick : () -> Unit ={}
){
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(Color.DarkGray),
        shape = RectangleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ){
        Text(
            text = content,
            color = Color.White,
            fontSize = 25.sp
        )
    }
}
@Preview
@Composable
fun TutorialScreen(
    onContinueClicked: () -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ){
            Text("Continue")
        }
    }
}

@Preview
@Composable
fun Boards(
    modifier: Modifier = Modifier,
    names:List<String> = List(1000){it.toString()}
){
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Board(name = name)
        }
    }
}

@Preview
@Composable
fun Board(
    name:String =""
){
    var expanded by remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }

            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.West else Icons.Filled.East,
                    contentDescription = if (expanded) "Show less" else "Show more"
                )
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