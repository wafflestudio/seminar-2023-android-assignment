package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wafflestudio.assignment4.databinding.FragmentMovieDetailBinding
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto

class MovieDetailFragment : Fragment() {

    companion object {
        private const val ARG_MOVIE = "arg_movie"

        fun newInstance(movie: MovieDetailDto): MovieDetailFragment {
            val args = Bundle().apply {
                putParcelable(ARG_MOVIE, movie)
            }
            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie: MovieDetailDto? = arguments?.getParcelable(ARG_MOVIE)
        movie?.let {
            binding.movieDetailTitle.text = it.title
            binding.movieDetailDirector.text = it.releaseDate
            binding.movieDetailDescription.text = it.overview
        }
    }
}