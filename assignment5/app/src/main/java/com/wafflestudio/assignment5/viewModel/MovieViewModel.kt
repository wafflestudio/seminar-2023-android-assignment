package com.wafflestudio.assignment5.viewModel

import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment5.model.Movie
import com.wafflestudio.assignment5.model.Review
import com.wafflestudio.assignment5.network.MyRestAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val api: MyRestAPI,
) : ViewModel() {

    val searchedMovieList: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val currentPage: MutableStateFlow<Int> = MutableStateFlow(1)
    val totalPages: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalResults: MutableStateFlow<Int> = MutableStateFlow(0)

    val searchedReviews: MutableStateFlow<List<Review>> = MutableStateFlow(emptyList())

    suspend fun search(
        query: String,
        includeAdult: Boolean,
        page: Int,
    ) {
        api.searchMovie(query, adult = includeAdult, page = page).apply {
            searchedMovieList.value = results
            currentPage.value = page
            this@MovieViewModel.totalPages.value = this.totalPages
            this@MovieViewModel.totalResults.value = this.totalResults
        }
    }

    suspend fun searchReview(id: Int, page: Int) {
        api.searchReview(id = id, page = page).apply {
            searchedReviews.value = this.results
            currentPage.value = page
            this@MovieViewModel.totalPages.value = this.totalPages
            this@MovieViewModel.totalResults.value = this.totalResults
        }
    }
}