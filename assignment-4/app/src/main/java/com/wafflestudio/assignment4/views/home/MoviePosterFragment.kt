package com.wafflestudio.assignment4.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.R
import com.wafflestudio.assignment4.databinding.FragmentMoviePosterBinding
import com.wafflestudio.assignment4.network.dto.movie.Movie

class MoviePosterFragment(private val movie: Movie?) : Fragment() {
    private lateinit var binding: FragmentMoviePosterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePosterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMovieInfo(movie)
    }

    private fun setMovieInfo(movie: Movie?) {
        if (movie == null) return
        val imageView = view?.findViewById<ImageView>(R.id.image) ?: return
        Glide.with(this).load("https://image.tmdb.org/t/p/original${movie.posterPath}")
            .fitCenter()
            .into(imageView)

        binding.title.text = movie.title
        binding.overview.text = movie.overview
        binding.releaseDate.text = "개봉일 : ${movie.releaseDate}"
        binding.score.text = "평점 : ${movie.voteAverage.toString()}"
        binding.showMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment)
        }
    }
}