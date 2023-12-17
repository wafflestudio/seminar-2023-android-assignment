package com.wafflestudio.assignment4.views.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.wafflestudio.assignment4.R
import com.wafflestudio.assignment4.databinding.FragmentHomeBinding
import com.wafflestudio.assignment4.utils.ZoomOutPageTransformer
import com.wafflestudio.assignment4.utils.eraseFromPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var viewPager: ViewPager2
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {
            context?.eraseFromPreference("token")
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadTop5Movies()
            withContext(Dispatchers.Main) {
                viewPager = view.findViewById(R.id.pager)
                viewPager.offscreenPageLimit = 4
                val pagerAdapter = ScreenSlidePagerAdapter(activity as AppCompatActivity)
                viewPager.apply {
                    adapter = pagerAdapter
                    setPageTransformer(ZoomOutPageTransformer())
                }
//                viewModel.movies.observe(this@HomeFragment as LifecycleOwner) {
//                    viewPager.adapter?.notifyDataSetChanged()
//                }
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: AppCompatActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int): Fragment = MoviePosterFragment(
            viewModel.movies.value!![position]
        )
    }

    companion object {
        const val NUM_PAGES = 5;
    }
}