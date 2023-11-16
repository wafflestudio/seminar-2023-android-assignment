package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.wafflestudio.assignment4.databinding.EmptyFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmptyFragment : Fragment() {

    private lateinit var binding: EmptyFragmentBinding
    private lateinit var movieViewPager: ViewPager2
    private lateinit var moviePagerAdapter: ViewAdapter

    private val viewModel: EmptyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EmptyFragmentBinding.inflate(inflater, container, false)
        setupButtonListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            setupViewPager(movies)
            moviePagerAdapter.notifyDataSetChanged()
        }
        loadMovies()
    }
    private fun setupViewPager(movies: List<MovieData>) {
        moviePagerAdapter = ViewAdapter(this, movies)
        movieViewPager = binding.pager
        movieViewPager.adapter = moviePagerAdapter
        movieViewPager.offscreenPageLimit = 5
    }

    private fun setupButtonListener() {
        binding.button.setOnClickListener {
            MyApplication.preferences.DeleteToken("token")
            MyApplication.preferences.SetToken("success", "false")
            findNavController().navigate(R.id.action_empty_fragment_to_login_fragment)
        }
    }


    private fun loadMovies() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModel.fetchMovies()
            } catch(e: Exception) {
                Log.e("Error", "Loading movie failed: ${e.message}")
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            moviePagerAdapter.notifyDataSetChanged()
        }
    }
}
