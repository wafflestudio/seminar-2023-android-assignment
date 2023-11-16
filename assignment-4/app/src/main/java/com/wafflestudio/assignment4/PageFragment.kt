package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.PageFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageFragment : Fragment() {
    private var _binding: PageFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: EmptyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PageFragmentBinding.inflate(inflater, container, false)
        initializeUI()
        return binding.root
    }

    private fun initializeUI() {
        val movieData = arguments?.getParcelable<MovieData>("data_movie")
        movieData?.let { showMovieDetails(it) }
    }

    private fun showMovieDetails(dataMovie:MovieData) {
        val posterUrl = "https://image.tmdb.org/t/p/original="+ dataMovie.posterUrl
        Glide.with(binding.root.context).load(posterUrl).into(binding.poster)
        binding.title.text = dataMovie.title
        binding.overview.text = dataMovie.overview
        binding.star.text = "평점: ${dataMovie.voteAverage}"
        binding.date.text = "개봉일: ${dataMovie.releaseDate}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
