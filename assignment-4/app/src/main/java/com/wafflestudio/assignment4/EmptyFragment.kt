package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.wafflestudio.assignment4.databinding.FragmentEmptyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val FIRST_PARAM = "firstParam"
private const val SECOND_PARAM = "secondParam"
-
@AndroidEntryPoint
class EmptyFragment @Inject constructor() : Fragment() {
    private var firstParam: String? = null
    private var secondParam: String? = null
    private lateinit var fragmentBinding: FragmentEmptyBinding
    private lateinit var moviesViewPager: ViewPager2
    private lateinit var moviesAdapter: MoviesPagerAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            firstParam = it.getString(FIRST_PARAM)
            secondParam = it.getString(SECOND_PARAM)
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchMovies()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentEmptyBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        observeMovieList()
        setupLogoutButton()
    }

    private fun setupViewPager() {
        moviesAdapter = MoviesPagerAdapter(viewModel.movieList, this)
        fragmentBinding.moviesViewPager.adapter = moviesAdapter
    }

    private fun observeMovieList() {
        viewModel.liveMovieList.observe(viewLifecycleOwner) {
            moviesAdapter.notifyDataSetChanged()
        }
    }

    private fun setupLogoutButton() {
        fragmentBinding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_emptyFragment_to_loginFragment)
            MyApplication.prefs.setString("token", "")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(firstParam: String, secondParam: String) =
            EmptyFragment().apply {
                arguments = Bundle().apply {
                    putString(FIRST_PARAM, firstParam)
                    putString(SECOND_PARAM, secondParam)
                }
            }
    }
}
