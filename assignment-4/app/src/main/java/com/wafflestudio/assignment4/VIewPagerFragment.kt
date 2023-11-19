package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.ViewpagerFragmentBinding

class VIewPagerFragment(private val movieData: MovieData): Fragment() {
    private lateinit var binding: ViewpagerFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ViewpagerFragmentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.title.text = movieData.title
        Glide.with(binding.poster.context)
            .load("https://image.tmdb.org/t/p/w300/"+ movieData.poster_path)
            .into(binding.poster)

        binding.overview.text = movieData.overview
        binding.date.text = movieData.release_date
        binding.vote.text = movieData.vote_average.toString()
        return binding.root
    }
}