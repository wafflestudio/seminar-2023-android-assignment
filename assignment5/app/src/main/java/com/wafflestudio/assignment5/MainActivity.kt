package com.wafflestudio.assignment5

import android.graphics.Movie
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.East
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.West
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.Glide
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

@Composable
fun IntroButton(
    content:String="",
    onclick : () -> Unit ={}
){
    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(Color.DarkGray),
        shape = RoundedCornerShape(20),
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
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(10)
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

@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun SearchScreen(){
    val mainViewModel = hiltViewModel<MainViewModel>()
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val movies =mainViewModel.movielist.collectAsState()
    var page by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        TextField(
            leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search") },
            value = text,
            onValueChange = {text = it},
            placeholder = { Text("시리즈, 영화를 검색해 보세요...") },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    page=1
                    focusManager.clearFocus()
                    CoroutineScope(Dispatchers.Main).launch {
                        mainViewModel.getmovies(text,page)
                    }
                }
            )
        )
        if(movies.value.page!=0) {
            Text(text = "${movies.value.total_results}개의 영화가 검색되었습니다.")
            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .height(590.dp)
            ) {
                items(items = movies.value.result) { movie ->
                    ShowMovie(movie = movie)
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                when(page>1) {
                    true -> {
                        TextButton(
                            onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    mainViewModel.getmovies(text, page)
                                }
                                page -= 1
                            }
                        ) {
                            Text(
                                text = "<- 이전 페이지",
                                color = Color.Black
                            )
                        }
                    }
                    false -> Spacer(modifier=Modifier.width(110.dp))
                }
                Text(text = page.toString())
                when(page < movies.value.total_pages){
                    true -> {
                        TextButton(
                            onClick = {
                                CoroutineScope(Dispatchers.Main).launch {
                                    mainViewModel.getmovies(text, page)
                                }
                                page += 1
                            }
                        ) {
                            Text(
                                text = "다음 페이지 ->",
                                color = Color.Black
                            )
                        }
                    }
                    false -> Spacer(modifier=Modifier.width(110.dp))
                }
            }
        }
    }
}


val moviedefault = MovieInfo(adult=false,
backdrop_path="/5WkP8mUzfnuKVjDLt9eTKobP3Cn.jpg",
genre_ids=arrayOf(28, 12, 878, 14, 53),
id= 15767,
original_language= "ja",
original_title= "ゴジラ ファイナルウォーズ",
overview= "Humanity finally rids themselves of Godzilla, imprisoning him in an icy tomb in the South Pole. All is peaceful until various monsters emerge to lay waste to Earth's cities. Overwhelmed, humanity is seemingly saved by a race of benevolent aliens known as Xiliens. But not all is what it seems with these bizarre visitors. If humanity wishes to survive, they must reluctantly resurrect their most hated enemy, Godzilla.",
popularity= 39.03,
poster_path= "/21lWA4YyYppVnwAPOiBkGrt2FGr.jpg",
release_date= "2004-12-04",
title= "Godzilla: Final Wars",
video= false,
vote_average= 7.0,
vote_count= 271)
@Preview
@Composable
fun ShowMovie(movie:MovieInfo= moviedefault){
    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 10.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(130.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
            ) {
                CoilImage(
                    imageModel = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = movie.original_title!!,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row{
                    Text(
                        text = "평점 : ${movie.vote_average}",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "개봉일 : ${movie.release_date}",
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ClockScreen(){
    var localDateTime:LocalDateTime
    var localTime by rememberSaveable { mutableStateOf("00 00 00") }
    
    LaunchedEffect(localTime) {
        while(true){
            localDateTime= LocalDateTime.now()
            localTime=localDateTime.format(DateTimeFormatter.ofPattern("HH mm ss")).toString()
            delay(1000)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row {
            Number(localTime[0].toString())
            Number(localTime[1].toString())
        }
        Text(
            text=":",
            fontSize = 50.sp,
            modifier = Modifier.padding(10.dp)
        )
        Row {
            Number(localTime[3].toString())
            Number(localTime[4].toString())
        }
        Text(
            text=":",
            fontSize = 50.sp,
            modifier = Modifier.padding(10.dp)
        )
        Row {
            Number(localTime[6].toString())
            Number(localTime[7].toString())
        }
    }
}

private val num:List<String> = listOf("1111110","0110000","1101101","1111001","0110011"
    ,"1011011","1011111","1110000","1111111","1111011")
@Preview
@Composable
fun Number(n:String="0"){
    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Column{
            Row {
                Spacer(modifier = Modifier.width(4.dp))
                NumSegment(filled = num[n.toInt()][0].toString(),0)
            }
            Row {
                NumSegment(filled = num[n.toInt()][5].toString(),1)
                Spacer(modifier = Modifier.width(30.dp))
                NumSegment(filled = num[n.toInt()][1].toString(),1)
            }
            Row {
                Spacer(modifier = Modifier.width(4.dp))
                NumSegment(filled = num[n.toInt()][6].toString(),0)
            }
            Row {
                NumSegment(filled = num[n.toInt()][4].toString(),1)
                Spacer(modifier = Modifier.width(30.dp))
                NumSegment(filled = num[n.toInt()][2].toString(),1)
            }
            Row {
                Spacer(modifier = Modifier.width(4.dp))
                NumSegment(filled = num[n.toInt()][3].toString(),0)
            }
        }
    }
}

@Preview
@Composable
fun NumSegment(filled:String="0",orientation:Int=0){
    Box(
        modifier = Modifier
            .height((orientation * 26 + 4).dp)
            .width((30 - orientation * 26).dp)
            .background(
                when (filled == "1") {
                    true -> Color.Black
                    false -> Color.White
                }
            )
    )
}