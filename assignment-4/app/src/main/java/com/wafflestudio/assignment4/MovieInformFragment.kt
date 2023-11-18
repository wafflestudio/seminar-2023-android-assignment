package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding


class MovieInformFragment(private val movie:Data.MovieInfo):Fragment() {

    private lateinit var binding:FragmentMovieBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("https://image.tmdb.org/t/p/original${movie.poster_path}")
        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/original${movie.poster_path}")
            .into(binding.poster)
        binding.title.text = "Title : " + movie.title
        binding.overview.text = "OverView : " +movie.overview
        binding.voteAverage.text = "Vote_Average : "+movie.vote_average


    }
}

class MovieInformAdapter(fragment: Fragment, private val movies: Data.Movies): FragmentStateAdapter(fragment) {
    override fun getItemCount():Int = 5
    override fun createFragment(position: Int): Fragment {
        return MovieInformFragment(movies.result[position])
    }
}