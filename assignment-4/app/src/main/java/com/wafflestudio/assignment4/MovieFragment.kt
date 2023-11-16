package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import com.wafflestudio.assignment4.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var viewPager : ViewPager2
    private lateinit var pagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("moviefragment","oncreate")
        super.onCreate(savedInstanceState)
        binding = FragmentMovieBinding.inflate(layoutInflater)
        viewPager = binding.pager
        pagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = pagerAdapter
        viewModel.fetchMovie()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("moviefragment","oncreaveview")
        binding.logoutBtn.setOnClickListener {
            viewModel.logout()
        }
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer { loginStatus ->
            if(loginStatus!="true") findNavController().navigate(R.id.action_movieFragment_to_loginFragment)
        })
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                pagerAdapter.submitList(it)
                pagerAdapter.notifyDataSetChanged()
                //굉장히 이상한 짓인 것 같지만, FragmentStateAdapter는 notifyDataSetChanged()를 호출했을떄
                //현재 페이지 frgment가 업데이트 되지 않는다고 해서 아래와 같이 처리했습니다 ㅜㅜ
                pagerAdapter.firstFragment.onUpdateData(it[0])
                Log.d("observe", it.toString())
            }
        })

        return binding.root

    }

}