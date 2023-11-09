package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.wafflestudio.assignment4.databinding.FragmentBlankBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BlankFragment : Fragment() {

    private lateinit var binding : FragmentBlankBinding
    private lateinit var pagerAdapter : ViewPagerAdapter
    private lateinit var viewPager : ViewPager2

    private val viewModel : BlankViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBlankBinding.inflate(layoutInflater)
        pagerAdapter = ViewPagerAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = pagerAdapter
        viewPager.offscreenPageLimit = 5
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.loadMovie()
            } catch(e : Exception){
                Log.d("error", e.message.toString())
            }
        }

        viewModel.movieList.observe(viewLifecycleOwner) {
            pagerAdapter.notifyDataSetChanged()/*data ->
            for(movie in data){

                val imageView = binding.poster
                Glide.with(requireContext()).load(movie.poster_path).into(imageView)
                binding.title.text = movie.title
                binding.overview.text = movie.overview
                binding.star.text = movie.vote_average.toString()
                binding.date.text = movie.release_data
            }*/
        }

        return binding.root
    }
}