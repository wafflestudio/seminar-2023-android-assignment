package com.wafflestudio.assignment4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wafflestudio.assignment4.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.home_fragment) {
    private lateinit var binding: HomeFragmentBinding
    private val viewModel : MainViewModel by viewModels()
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
            viewModel.logout()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        return binding.root
    }


}