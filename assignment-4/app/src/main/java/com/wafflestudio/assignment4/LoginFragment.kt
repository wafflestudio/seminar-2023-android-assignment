package com.wafflestudio.assignment4

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wafflestudio.assignment4.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        _binding=LoginFragmentBinding.inflate(layoutInflater)
        arguments?.let{
            param1=it.getString(ARG_PARAM1)
            param2=it.getString(ARG_PARAM2)
        }
    }
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loginButton.setOnClickListener {
            val key : String = binding.apiKeyEditText.text.toString()
            findNavController().navigate(R.id.action_login_fragment_to_empty_fragment)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    viewModel.performLogin(key)
                } catch (e: HttpException) {
                        Log.e("Login Error", e.message ?: "An error occurred")
                    }
                }
            }

        viewModel.loginToken.observe(viewLifecycleOwner) { token ->
            if (token != null) {
                if (token.isNotEmpty()) {
                    findNavController().navigate(R.id.action_login_fragment_to_empty_fragment)
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
