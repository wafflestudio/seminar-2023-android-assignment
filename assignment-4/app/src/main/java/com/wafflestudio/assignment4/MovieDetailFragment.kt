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

        // 전달받은 영화 정보를 바탕으로 UI 업데이트
        val movieTitle = arguments?.getString("title")
        val releaseDate = arguments?.getString("release_date")
        val overview = arguments?.getString("overview")

        // UI 업데이트s
        binding.movieDetailTitle.text = movieTitle
        binding.movieDetailDirector.text = releaseDate
        binding.movieDetailDescription.text = overview
    }
}