package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageFragment : Fragment() {
    private lateinit var binding : FragmentPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPageBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Log.d("pageFragment", "called")
        arguments?.takeIf { it.containsKey("data_movie") }?.apply {
            val dataMovie: DataMovie? = arguments?.getParcelable("data_movie")
            //Log.d("pageFragment_movie", (dataMovie==null).toString())
            if (dataMovie != null) {
                //Log.d("pageFragment_binding", "done")
                val imageView = binding.poster
                val path = "https://image.tmdb.org/t/p/original/" + dataMovie.poster_path
                Glide.with(binding.root.context).load(path).into(imageView)
                binding.title.text = dataMovie.title
                binding.overview.text = dataMovie.overview
                binding.star.text = "평점: " + dataMovie.vote_average.toString()
                binding.date.text = "개봉일: " + dataMovie.release_data
            }
        }
    }
}