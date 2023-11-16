package com.wafflestudio.assignment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
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

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: HomeAdapter
    private lateinit var movieList: List<MovieDetailDto>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        viewPager = rootView.findViewById(R.id.home_fragment_viewpager2)

        lifecycleScope.launch(Dispatchers.IO) {
            // 비동기 작업을 수행할 코루틴 블록
            movieList = viewModel.getMovieDetails("en-US", 1)
            withContext(Dispatchers.Main) {
                // 메인 스레드에서 수행할 작업

            }
        }

        adapter = HomeAdapter(MovieDetailFragment(), movieList)

        // ViewPager2에 어댑터 설정
        viewPager.adapter = adapter

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 여기에서 바인딩된 뷰를 사용하여 작업 수행
//        binding.loginFragmentBtn.setOnClickListener() {
//            binding.loginFragmentApiInput.toString() // 을 sharedPreference에 저장.
//        }// 예시로 사용자명 EditText에 접근
    }

    override fun onDestroyView() { // 메모리 누수 방지
        super.onDestroyView()
        _binding = null
    }
}