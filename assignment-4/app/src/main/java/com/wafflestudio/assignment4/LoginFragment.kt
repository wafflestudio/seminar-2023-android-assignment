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
import com.wafflestudio.assignment4.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.submitBtn.setOnClickListener {
           viewModel.authenticate(binding.apiKey.text.toString())
        }

        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer { loginStatus ->
            if(loginStatus=="true") {
                findNavController().navigate(R.id.action_loginFragment_to_movieFragment)
            }
        })

        return binding.root
    }


}