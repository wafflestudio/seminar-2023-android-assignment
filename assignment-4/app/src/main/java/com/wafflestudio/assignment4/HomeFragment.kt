package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.wafflestudio.assignment4.databinding.ActivityMainBinding
import com.wafflestudio.assignment4.databinding.FragmentHomeBinding
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieDetailFragment: MovieDetailFragment = MovieDetailFragment()

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = binding.homeFragmentViewpager2
        val cubeOutRotationTransformer = CubeOutRotationTransformer()

        lifecycleScope.launch(Dispatchers.IO) {
            // 비동기 작업을 수행할 코루틴 블록
            withContext(Dispatchers.Main) {
                adapter = HomeAdapter(this@HomeFragment, viewModel.getMovieDetails("en-US", 1))
                viewPager.adapter = adapter
                viewPager.offscreenPageLimit = 2
                viewPager.setPageTransformer(cubeOutRotationTransformer)
                viewModel.movieList.observe(viewLifecycleOwner, Observer { movieList ->
                    // movieList가 업데이트될 때마다 실행되는 부분
                    adapter.setItems(movieList)
                    adapter.notifyItemInserted(0)
                })
            }
        }

        binding.logoutBtn.setOnClickListener() {
            Log.d("HF", "logoutBtn Clicked")
            viewModel.deleteApiKey()
            val loginFragment = LoginFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_view, loginFragment)
                .commit()
        }

    }

    override fun onDestroyView() { // 메모리 누수 방지
        super.onDestroyView()
        _binding = null
    }
}