package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentPageBinding

private const val ARG_MOVIE = "movie"

class PageFragment(private val movieDetail: MovieInfo) : Fragment() {
    private lateinit var pageBinding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pageBinding = FragmentPageBinding.inflate(inflater, container, false)
        return pageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayMovieInfo()
    }

    private fun displayMovieInfo() {
        Glide.with(pageBinding.root.context)
            .load("https://image.tmdb.org/t/p/original/${movieDetail.posterPath}")
            .into(pageBinding.moviePoster)
        pageBinding.movieTitle.text = movieDetail.movieTitle
        pageBinding.movieOverview.text = movieDetail.overviewDescription
        pageBinding.movieRating.text = "평점 : ${movieDetail.averageVote}"
        pageBinding.movieReleaseDate.text = "개봉일 : ${movieDetail.releaseDate}"
    }
}
