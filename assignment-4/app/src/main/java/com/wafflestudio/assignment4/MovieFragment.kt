package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment(val movie:MovieInfo) : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("aaaa",movie.title)
        Glide.with(binding.root.context)
            .load("https://image.tmdb.org/t/p/original/"+movie.poster_path)
            .into(binding.poster)
        binding.title.text=movie.title
        binding.overview.text=movie.overview
        binding.star.text="평점 : " + movie.vote_average.toString()
        binding.date.text="개봉일 : " + movie.release_date
    }
}