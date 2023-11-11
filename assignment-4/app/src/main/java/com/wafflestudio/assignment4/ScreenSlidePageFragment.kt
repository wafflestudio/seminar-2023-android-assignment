package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentScreenSlidePageBinding

class ScreenSlidePageFragment(private val data : MovieData?) : Fragment() {

    private lateinit var binding : FragmentScreenSlidePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentScreenSlidePageBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.title.text = data?.original_title
        Glide.with(binding.poster.context)
            .load("https://image.tmdb.org/t/p/w300/"+data?.poster_path)
            .into(binding.poster)

        binding.overview.text = data?.overview
        binding.star.text = data?.vote_average.toString()
        binding.date.text = data?.release_date

        return binding.root
    }
}