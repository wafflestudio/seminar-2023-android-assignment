package com.wafflestudio.assignment5


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearchClicked : (String) -> Unit,
){
    var text by rememberSaveable {
        mutableStateOf("")
    }
    BasicTextField(
        value = text,
        onValueChange = { newText -> text = newText},
        modifier = Modifier
                .padding(12.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onSearchClicked(text)
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                .padding(horizontal = 50.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFD2F3F2),
                            shape = RoundedCornerShape(size = 16.dp)
                        )
                        .padding(all = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.DarkGray,
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    innerTextField()
                }
            }
        )
}
@Composable
fun MovieScreen(
    text : String,
){
    val viewModel = hiltViewModel<MainViewModel>()
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit){
        scope.launch(Dispatchers.IO){
            viewModel.getMovie(text)
        }
    }
    val movies = viewModel.movies.collectAsState()

    LazyColumn(){
        items(movies.value.size){index ->
            MovieContent(movie = movies.value[index])
        }
    }

}

@Composable
fun MovieContent(
    movie: MovieData,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp).border(1.dp, Color.Black)
            /*.animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )*/
    ){
        GlideImage(
            modifier = Modifier.size(150.dp).border(1.dp, Color.Black).background(Color.Yellow),
            imageModel = "https://image.tmdb.org/t/p/w500/${movie.path.toString()}",
            //error = ImageBitmap.imageResource(R.drawable.ic_launcher_background)
        )
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = movie.title.toString(), fontWeight = FontWeight.ExtraBold)
            Text(text = "개봉일: ${movie.date.toString()}")
            Text(text = "평점: ${movie.vote.toString()}")
        }
    }

}