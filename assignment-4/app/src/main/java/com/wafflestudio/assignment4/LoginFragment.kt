package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

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

        // 여기에서 바인딩된 뷰를 사용하여 작업 수행
        binding.loginFragmentBtn.setOnClickListener() {
            lifecycleScope.launch(Dispatchers.IO) {
                // 비동기 작업을 수행할 코루틴 블록
                Log.d("LoginFrag", "비동기 진입 + ${binding.loginFragmentApiInput.text.toString()}")
                val apiKey = "Bearer " + binding.loginFragmentApiInput.text.toString()
                var loginResult = "false"
                withContext(Dispatchers.Main) {
                    Log.d("LoginFrag", "$apiKey")
                    viewModel.checkApiKey(apiKey)
                    viewModel.apiKeyValid.observe(viewLifecycleOwner, Observer { apiKeyValid ->
                        loginResult = apiKeyValid.toString()
                        Log.d("LoginFrag", "login Result: $loginResult")
                        when (loginResult) {
                            "true" -> {Toast.makeText(requireContext(), "Login Success.", Toast.LENGTH_SHORT).show()
                                replaceHomeFragment() }
                            "false" -> Toast.makeText(requireContext(), "Wrong Access.", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(requireContext(), "HttpError", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun replaceHomeFragment() {
        Log.d("LF", "changed to HomeFragment")
        val homeFragment = HomeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_view, homeFragment)
            .commit()
    }
}