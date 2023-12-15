package com.wafflestudio.assignment5.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wafflestudio.assignment5.model.Review
import com.wafflestudio.assignment5.viewModel.MovieViewModel
import kotlinx.coroutines.launch

@Composable
fun MovieReviewPage(id: Int) {
    val viewModel: MovieViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    val searchResult by viewModel.searchedReviews.collectAsState()
    val currentPage by viewModel.currentPage.collectAsState()
    val totalPages by viewModel.totalPages.collectAsState()
    val totalResults by viewModel.totalResults.collectAsState()

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.searchReview(id, 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            item {
                Text(text = "${totalResults}개의 리뷰가 검색되었습니다.", fontSize = 14.sp, color = Color.White)
            }
            items(searchResult, key = { it.id }) {
                ReviewItem(review = it)
                Divider()
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
                                viewModel.searchReview(id, currentPage - 1)
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
                                viewModel.searchReview(id, currentPage + 1)
                            }
                        },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun ReviewItem(review: Review) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = review.author,
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = review.authorDetail.rating ?: "평점 없음",
                fontSize = 16.sp,
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
            )
        }
        Text(
            text = review.content,
            fontSize = 18.sp,
            color = Color.White,
            lineHeight = 25.sp,
            maxLines = if (expanded) Int.MAX_VALUE else 5,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable {
                expanded = expanded.not()
            }
        )
    }
}