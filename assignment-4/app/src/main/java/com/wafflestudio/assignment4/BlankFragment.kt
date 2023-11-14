package com.wafflestudio.assignment4

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.button.setOnClickListener{
            MyApplication.preferences.removeToken("token")
            MyApplication.preferences.setToken("success", "false")
            findNavController().navigate(R.id.action_blankFragment_to_loginFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                viewModel.loadMovie()
            } catch(e : Exception){
                Log.d("error2", e.message.toString())
            }
        }

        viewModel.movieList.observe(viewLifecycleOwner) {
            Log.d("movieList", viewModel.movieList.value!!.size.toString())
            pagerAdapter = ViewPagerAdapter(this, viewModel.movieList.value!!)

            viewPager = binding.pager
            viewPager.adapter = pagerAdapter
            viewPager.offscreenPageLimit = 5
            Log.d("end", "called")
        }
    }
}