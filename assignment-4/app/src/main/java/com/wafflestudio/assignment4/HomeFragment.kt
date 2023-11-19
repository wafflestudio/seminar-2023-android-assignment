package com.wafflestudio.assignment4

import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.wafflestudio.assignment4.databinding.HomeFragmentBinding
import com.wafflestudio.assignment4.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel : MainViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var movieList: List<MovieData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.logoutButton.setOnClickListener{
            Log.d("aaaa","Logout button clicked")
            viewModel.logout()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val key = Myapplication.preferences.getToken("Token")
        CoroutineScope(Dispatchers.IO).launch {
            movieList = viewModel.getMovie(key)
            withContext(Dispatchers.Main){
                viewPagerAdapter = ViewPagerAdapter(this@HomeFragment, movieList)
                viewPager = view.findViewById(R.id.pager)
                viewPager.adapter = viewPagerAdapter
            }
        }


    }

}