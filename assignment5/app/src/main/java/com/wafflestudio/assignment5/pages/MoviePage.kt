package com.wafflestudio.assignment5.pages

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.wafflestudio.assignment5.R
import com.wafflestudio.assignment5.model.Movie
import com.wafflestudio.assignment5.viewModel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MoviePage(
    toDetail: (Movie) -> Unit,
) {
    val viewModel: MovieViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val searchResult by viewModel.searchedMovieList.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()
    val totalPages by viewModel.totalPages.collectAsState()
    val totalResults by viewModel.totalResults.collectAsState()

    var query by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        BasicTextField(
            value = query,
            onValueChange = { newValue ->
                query = newValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textStyle = TextStyle(color = Color(176, 176, 176), fontSize = 22.sp),
            decorationBox = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(29, 29, 29))
                        .padding(5.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "",
                            modifier = Modifier.size(30.dp),
                            colorFilter = ColorFilter.tint(Color(169, 169, 169))
                        )
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            it()
                            if (query.isEmpty()) {
                                Text(
                                    text = "시리즈, 영화를 검색해 보세요...",
                                    fontSize = 18.sp,
                                    color = Color(176, 176, 176)
                                )
                            }
                        }
                    }
                }
            },
            keyboardActions = KeyboardActions(onSearch = {
                scope.launch(Dispatchers.IO) {
                    viewModel.search(query, true, 1)
                }
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        )
        if (searchResult.isNotEmpty()) {
            Text(text = "${totalResults}개의 영화가 검색되었습니다.", fontSize = 14.sp, color = Color.White)
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(searchResult) {
                MovieItem(movie = it, onClick = { toDetail(it) })
            }
        }
        if (searchResult.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = if (currentPage != 1) "<- 이전 페이지" else "         ",
                    fontSize = 16.sp,
                    color = Color(169, 169, 169),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            scope.launch {
                                viewModel.search(query, true, currentPage - 1)
                            }
                        },
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = currentPage.toString(),
                    fontSize = 16.sp,
                    color = Color(169, 169, 169),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = if (currentPage != totalPages) "다음 페이지 ->" else "         ",
                    fontSize = 16.sp,
                    color = Color(169, 169, 169),
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            scope.launch {
                                viewModel.search(query, true, currentPage + 1)
                            }
                        },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
            contentDescription = "",
            modifier = Modifier
                .height(120.dp)
                .widthIn(max = 80.dp),
            contentScale = ContentScale.FillHeight,
            placeholder = ColorPainter(color = Color(0x11, 0x11, 0x11))
        )
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = movie.title,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (movie.adult) {
                    Box(
                        modifier = Modifier
                            .border(2.dp, color = Color.Red, shape = CircleShape)
                            .padding(5.dp)
                    ) {
                        Text(text = "19", fontSize = 10.sp, color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(text = "평점 : ${movie.voteAverage}", fontSize = 14.sp, color = Color.White)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "개봉일: ${movie.releaseDate}", fontSize = 14.sp, color = Color.White)
            }
        }
    }
}