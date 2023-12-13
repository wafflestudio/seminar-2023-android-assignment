package com.wafflestudio.assignment5.components.compose.movie

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.wafflestudio.assignment5.MovieViewModel
import com.wafflestudio.assignment5.lib.network.dto.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchBar(modifier: Modifier) {
    var text by remember { mutableStateOf("") }
    val movieViewModel: MovieViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    Row (modifier = Modifier.fillMaxWidth()) {
        TextField (modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // 엔터 키를 Done으로 설정합니다.
            ),
            keyboardActions = KeyboardActions(onDone = {
                coroutineScope.launch(Dispatchers.IO) {
                    movieViewModel.getMovie(text)
                }
            }),
            leadingIcon = {
                Icon (
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            }
        )
    }

}