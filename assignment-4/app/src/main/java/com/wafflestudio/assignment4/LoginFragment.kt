package com.wafflestudio.assignment4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        // 여기에서 바인딩된 뷰를 사용하여 작업 수행
        binding.loginFragmentBtn.setOnClickListener() {
            val enteredAPIkey = binding.loginFragmentApiInput.toString()
            viewModel.saveApiKey(enteredAPIkey, sharedPref) // 을 sharedPreference에 저장.
        }

        lifecycleScope.launch(Dispatchers.IO) {
            // 비동기 작업을 수행할 코루틴 블록
            val passed = viewModel.
            withContext(Dispatchers.Main) {
                // 메인 스레드에서 수행할 작업

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}