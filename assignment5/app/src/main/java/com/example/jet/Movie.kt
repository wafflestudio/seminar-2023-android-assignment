package com.example.jet

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter

private val token="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MDA3YTQ0MjU1NGM5ZTNiM2E1NmVhNzQ1MmVkYTNjYiIsInN1YiI6IjY1NTcxODg0ZWE4NGM3MTA5MjI4OTFkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tk7NnUpEzXoD-8aaCJw4L-neabtM56D_-WvgB2T50eo"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Movie(viewModel: MyViewModel){


    var text by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(5.dp, color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Text") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    viewModel.getMoviesByQuery(text.text,viewModel.page)
                    Log.d("aaaa","search tried")
                }
            ),
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Text("Entered Text: ${text.text}")
        LazyColumn{
            items(viewModel.myMovieList.value){
                MovieCard("https://image.tmdb.org/t/p/w500/${it.posterPath}",it.title,it.voteAverage,it.releaseDate)
            }
        }
    }

}

@Composable
fun MovieCard(imageUrl:String,title:String, rating:Double,releaseDate:String){

    // Use Coil's rememberImagePainter to load the image from the URL
    val painter = rememberImagePainter(data = imageUrl)
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(5.dp)
    ){
        Row{
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
            )
            Column {
                Text(title)
                Row {
                    Text("평점:$rating")
                    Spacer(modifier = Modifier.size(30.dp))
                    Text("개봉일:$releaseDate")
                }
            }
        }
    }
}